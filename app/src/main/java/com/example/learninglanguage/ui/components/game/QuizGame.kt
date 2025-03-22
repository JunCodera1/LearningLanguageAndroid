package com.example.learninglanguage.ui.components.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learninglanguage.data.model.WordData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 1. Main QuizGame Component
@Composable
fun QuizGame() {
    val questions = remember { getSampleQuestions() }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var isAnswerCorrect by remember { mutableStateOf<Boolean?>(null) }
    var score by remember { mutableStateOf(0) }
    var showResults by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showResults) {
            // Show final results
            ResultsView(
                score = score,
                totalQuestions = questions.size,
                onPlayAgain = {
                    currentQuestionIndex = 0
                    selectedAnswer = null
                    isAnswerCorrect = null
                    score = 0
                    showResults = false
                }
            )
        } else {
            // Quiz content
            QuizContent(
                questions = questions,
                currentQuestionIndex = currentQuestionIndex,
                selectedAnswer = selectedAnswer,
                isAnswerCorrect = isAnswerCorrect,
                score = score,
                onAnswerSelected = { option ->
                    selectedAnswer = option
                    isAnswerCorrect = option == questions[currentQuestionIndex].translation

                    if (option == questions[currentQuestionIndex].translation) {
                        score += 1
                    }

                    // Move to next question after delay
                    coroutineScope.launch {
                        delay(1500)
                        if (currentQuestionIndex < questions.size - 1) {
                            currentQuestionIndex++
                            selectedAnswer = null
                            isAnswerCorrect = null
                        } else {
                            showResults = true
                        }
                    }
                }
            )
        }
    }
}

// 2. Quiz Content Component
@Composable
fun QuizContent(
    questions: List<WordData>,
    currentQuestionIndex: Int,
    selectedAnswer: String?,
    isAnswerCorrect: Boolean?,
    score: Int,
    onAnswerSelected: (String) -> Unit
) {
    val currentQuestion = questions[currentQuestionIndex]

    // Quiz progress
    QuizProgress(
        currentQuestionIndex = currentQuestionIndex,
        totalQuestions = questions.size,
        score = score
    )

    // Question card
    QuestionCard(question = currentQuestion)

    Spacer(modifier = Modifier.height(24.dp))

    // Answer options
    AnswerOptions(
        options = currentQuestion.options,
        correctAnswer = currentQuestion.translation,
        selectedAnswer = selectedAnswer,
        isAnswerCorrect = isAnswerCorrect,
        onAnswerSelected = onAnswerSelected
    )
}

// 3. Quiz Progress Component
@Composable
fun QuizProgress(
    currentQuestionIndex: Int,
    totalQuestions: Int,
    score: Int
) {
    LinearProgressIndicator(
        progress = (currentQuestionIndex + 1).toFloat() / totalQuestions,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )

    Text(
        text = "Question ${currentQuestionIndex + 1}/$totalQuestions",
        modifier = Modifier.padding(bottom = 8.dp)
    )

    Text(
        text = "Score: $score",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

// 4. Question Card Component
@Composable
fun QuestionCard(question: WordData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "What's the meaning of:",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = question.word,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "\"${question.example}\"",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// 5. Answer Options Component
@Composable
fun AnswerOptions(
    options: List<String>,
    correctAnswer: String,
    selectedAnswer: String?,
    isAnswerCorrect: Boolean?,
    onAnswerSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { option ->
            AnswerOption(
                option = option,
                isSelected = selectedAnswer == option,
                isCorrectAnswer = option == correctAnswer,
                isAnswered = isAnswerCorrect != null,
                onSelect = { onAnswerSelected(option) }
            )
        }
    }
}

// 6. Individual Answer Option Component
@Composable
fun AnswerOption(
    option: String,
    isSelected: Boolean,
    isCorrectAnswer: Boolean,
    isAnswered: Boolean,
    onSelect: () -> Unit
) {
    val backgroundColor = when {
        !isAnswered -> if (isSelected)
            MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        isCorrectAnswer -> MaterialTheme.colorScheme.primaryContainer
        isSelected -> MaterialTheme.colorScheme.errorContainer
        else -> MaterialTheme.colorScheme.surface
    }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isAnswered) { onSelect() },
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        border = BorderStroke(
            width = 2.dp,
            color = when {
                !isAnswered && isSelected -> MaterialTheme.colorScheme.primary
                isAnswered && isCorrectAnswer -> MaterialTheme.colorScheme.primary
                isSelected && !isCorrectAnswer -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = option,
                fontSize = 18.sp
            )

            if (isAnswered) {
                if (isCorrectAnswer) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Correct",
                        tint = MaterialTheme.colorScheme.primary
                    )
                } else if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Incorrect",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

// 7. Results View Component
@Composable
fun QuizResultsView(
    score: Int,
    totalQuestions: Int,
    onPlayAgain: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Quiz Completed!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Your Score: $score/$totalQuestions",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "${(score.toFloat() / totalQuestions * 100).toInt()}%",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedCard(
            modifier = Modifier
                .clickable { onPlayAgain() }
                .padding(16.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Play Again",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

// 8. Sample Data Function
private fun getSampleQuestions(): List<WordData> {
    return listOf(
        WordData(
            word = "Hello",
            translation = "Xin chào",
            example = "Hello, how are you?",
            options = listOf("Tạm biệt", "Xin chào", "Cảm ơn", "Không")
        ),
        WordData(
            word = "Goodbye",
            translation = "Tạm biệt",
            example = "Goodbye, see you tomorrow.",
            options = listOf("Tạm biệt", "Xin chào", "Cảm ơn", "Không")
        ),
        WordData(
            word = "Thank you",
            translation = "Cảm ơn",
            example = "Thank you for your help.",
            options = listOf("Tạm biệt", "Xin chào", "Cảm ơn", "Không")
        ),
        WordData(
            word = "Yes",
            translation = "Có",
            example = "Yes, I understand.",
            options = listOf("Tạm biệt", "Xin chào", "Cảm ơn", "Có")
        ),
        WordData(
            word = "No",
            translation = "Không",
            example = "No, I don't want that.",
            options = listOf("Tạm biệt", "Xin chào", "Không", "Có")
        )
    )
}