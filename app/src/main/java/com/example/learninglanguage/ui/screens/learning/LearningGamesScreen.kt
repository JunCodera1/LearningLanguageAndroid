package com.example.learninglanguage.ui.screens.learning

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class GameType {
    QUIZ,
    WORD_MATCHING,
    FILL_BLANKS
}

data class WordData(
    val word: String,
    val translation: String,
    val example: String,
    val options: List<String> = emptyList()
)

@Composable
fun LearningGamesScreen() {
    var currentGameType by remember { mutableStateOf(GameType.QUIZ) }
    var showGameSelector by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (showGameSelector) {
            GameSelectorView(
                onGameSelected = { gameType ->
                    currentGameType = gameType
                    showGameSelector = false
                }
            )
        } else {
            // Show back button
            IconButton(
                onClick = {
                    showGameSelector = true
                },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Game Selection"
                )
            }

            // Show selected game
            when (currentGameType) {
                GameType.QUIZ -> QuizGame()
                GameType.WORD_MATCHING -> WordMatchingGame()
                GameType.FILL_BLANKS -> FillBlanksGame()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameSelectorView(onGameSelected: (GameType) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Learning Games",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ElevatedCard(
                    onClick = { onGameSelected(GameType.QUIZ) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Quiz,
                            contentDescription = "Quiz",
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Quick Quiz",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Test your knowledge with multiple choice questions",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            item {
                ElevatedCard(
                    onClick = { onGameSelected(GameType.WORD_MATCHING) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Compare,
                            contentDescription = "Word Matching",
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Word Matching",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Match words with their correct translations",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            item {
                ElevatedCard(
                    onClick = { onGameSelected(GameType.FILL_BLANKS) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.TextFields,
                            contentDescription = "Fill Blanks",
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.tertiary
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Fill in the Blanks",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Complete sentences by filling in missing words",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizGame() {
    val questions = remember {
        listOf(
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

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var isAnswerCorrect by remember { mutableStateOf<Boolean?>(null) }
    var score by remember { mutableStateOf(0) }
    var showResults by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val currentQuestion = questions[currentQuestionIndex]

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
            // Quiz progress
            LinearProgressIndicator(
                progress = (currentQuestionIndex + 1).toFloat() / questions.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Question ${currentQuestionIndex + 1}/${questions.size}",
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Score: $score",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Question card
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
                        text = currentQuestion.word,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "\"${currentQuestion.example}\"",
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Answer options
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                currentQuestion.options.forEach { option ->
                    val isSelected = selectedAnswer == option
                    val backgroundColor = when {
                        isAnswerCorrect == null -> if (isSelected)
                            MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                        option == currentQuestion.translation -> MaterialTheme.colorScheme.primaryContainer
                        isSelected && option != currentQuestion.translation -> MaterialTheme.colorScheme.errorContainer
                        else -> MaterialTheme.colorScheme.surface
                    }

                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = isAnswerCorrect == null) {
                                selectedAnswer = option
                                isAnswerCorrect = option == currentQuestion.translation

                                if (option == currentQuestion.translation) {
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
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = backgroundColor
                        ),
                        border = BorderStroke(
                            width = 2.dp,
                            color = when {
                                isAnswerCorrect == null && isSelected -> MaterialTheme.colorScheme.primary
                                isAnswerCorrect != null && option == currentQuestion.translation -> MaterialTheme.colorScheme.primary
                                isSelected && option != currentQuestion.translation -> MaterialTheme.colorScheme.error
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

                            if (isAnswerCorrect != null) {
                                if (option == currentQuestion.translation) {
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
            }
        }
    }
}

@Composable
fun WordMatchingGame() {
    val wordPairs = remember {
        listOf(
            Pair("Hello", "Xin chào"),
            Pair("Goodbye", "Tạm biệt"),
            Pair("Thank you", "Cảm ơn"),
            Pair("Yes", "Có"),
            Pair("No", "Không")
        )
    }

    var shuffledWords by remember {
        mutableStateOf(wordPairs.map { it.second }.shuffled())
    }

    var selectedLeftIndex by remember { mutableStateOf<Int?>(null) }
    var selectedRightIndex by remember { mutableStateOf<Int?>(null) }

    var matchedPairs by remember { mutableStateOf(setOf<Int>()) }
    var score by remember { mutableStateOf(0) }
    var attempts by remember { mutableStateOf(0) }
    var gameCompleted by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(selectedLeftIndex, selectedRightIndex) {
        if (selectedLeftIndex != null && selectedRightIndex != null) {
            // Check if the match is correct
            val leftWord = wordPairs[selectedLeftIndex!!].first
            val rightWord = shuffledWords[selectedRightIndex!!]
            val correctMatch = wordPairs[selectedLeftIndex!!].second == rightWord

            attempts++

            if (correctMatch) {
                matchedPairs = matchedPairs + selectedLeftIndex!!
                score++

                // Check if game is completed
                if (matchedPairs.size == wordPairs.size) {
                    gameCompleted = true
                }
            }

            // Reset selections after a delay
            delay(1000)
            selectedLeftIndex = null
            selectedRightIndex = null
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (gameCompleted) {
            // Show results
            ResultsView(
                score = score,
                totalQuestions = wordPairs.size,
                onPlayAgain = {
                    shuffledWords = wordPairs.map { it.second }.shuffled()
                    selectedLeftIndex = null
                    selectedRightIndex = null
                    matchedPairs = setOf()
                    score = 0
                    attempts = 0
                    gameCompleted = false
                }
            )
        } else {
            // Game header
            Text(
                text = "Match the Words",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "Score: $score | Attempts: $attempts",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Word matching columns
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Left column (English words)
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "English",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    wordPairs.forEachIndexed { index, pair ->
                        val isMatched = matchedPairs.contains(index)
                        val isSelected = selectedLeftIndex == index

                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .clickable(enabled = !isMatched && selectedLeftIndex != index) {
                                    selectedLeftIndex = index
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = when {
                                    isMatched -> MaterialTheme.colorScheme.primaryContainer
                                    isSelected -> MaterialTheme.colorScheme.secondaryContainer
                                    else -> MaterialTheme.colorScheme.surface
                                }
                            ),
                            border = BorderStroke(
                                width = 2.dp,
                                color = when {
                                    isMatched -> MaterialTheme.colorScheme.primary
                                    isSelected -> MaterialTheme.colorScheme.secondary
                                    else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                                }
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = pair.first,
                                    fontSize = 16.sp,
                                    fontWeight = if (isMatched) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }

                // Right column (translated words)
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Tiếng Việt",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    shuffledWords.forEachIndexed { index, word ->
                        val correspondingLeftIndex = wordPairs.indexOfFirst { it.second == word }
                        val isMatched = matchedPairs.contains(correspondingLeftIndex)
                        val isSelected = selectedRightIndex == index

                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .clickable(enabled = !isMatched && selectedRightIndex != index) {
                                    selectedRightIndex = index
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = when {
                                    isMatched -> MaterialTheme.colorScheme.primaryContainer
                                    isSelected -> MaterialTheme.colorScheme.secondaryContainer
                                    else -> MaterialTheme.colorScheme.surface
                                }
                            ),
                            border = BorderStroke(
                                width = 2.dp,
                                color = when {
                                    isMatched -> MaterialTheme.colorScheme.primary
                                    isSelected -> MaterialTheme.colorScheme.secondary
                                    else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                                }
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = word,
                                    fontSize = 16.sp,
                                    fontWeight = if (isMatched) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FillBlanksGame() {
    val sentences = remember {
        listOf(
            "I ___ to school every day." to "go",
            "She ___ English very well." to "speaks",
            "They ___ in a big house." to "live",
            "We ___ breakfast at 7 AM." to "eat",
            "He ___ to music after work." to "listens"
        )
    }

    var currentSentenceIndex by remember { mutableStateOf(0) }
    var userAnswer by remember { mutableStateOf("") }
    var isAnswerChecked by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }
    var showResults by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val currentSentence = sentences[currentSentenceIndex]
    val displaySentence = currentSentence.first
    val correctAnswer = currentSentence.second

    // Extract text before and after blank
    val parts = displaySentence.split("___")
    val beforeBlank = parts[0]
    val afterBlank = if (parts.size > 1) parts[1] else ""

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showResults) {
            // Show final results
            ResultsView(
                score = score,
                totalQuestions = sentences.size,
                onPlayAgain = {
                    currentSentenceIndex = 0
                    userAnswer = ""
                    isAnswerChecked = false
                    score = 0
                    showResults = false
                }
            )
        } else {
            // Game progress
            LinearProgressIndicator(
                progress = (currentSentenceIndex + 1).toFloat() / sentences.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Sentence ${currentSentenceIndex + 1}/${sentences.size}",
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Score: $score",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Sentence card
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
                        text = "Fill in the blank:",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Display sentence with blank
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = beforeBlank,
                            fontSize = 18.sp
                        )

                        // User input or answer display
                        if (isAnswerChecked) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .background(
                                        color = if (isCorrect)
                                            MaterialTheme.colorScheme.primaryContainer
                                        else
                                            MaterialTheme.colorScheme.errorContainer,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = userAnswer,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCorrect)
                                        MaterialTheme.colorScheme.onPrimaryContainer
                                    else
                                        MaterialTheme.colorScheme.onErrorContainer
                                )
                            }
                        } else {
                            OutlinedTextField(
                                value = userAnswer,
                                onValueChange = { userAnswer = it },
                                singleLine = true,
                                modifier = Modifier.width(120.dp),
                                textStyle = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Text(
                            text = afterBlank,
                            fontSize = 18.sp
                        )
                    }

                    // Show correct answer if wrong
                    AnimatedVisibility(
                        visible = isAnswerChecked && !isCorrect,
                        enter = fadeIn(animationSpec = tween(300)),
                        exit = fadeOut(animationSpec = tween(300))
                    ) {
                        Text(
                            text = "Correct answer: $correctAnswer",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Check answer or continue button
            Button(
                onClick = {
                    if (!isAnswerChecked) {
                        // Check the answer
                        isAnswerChecked = true
                        isCorrect = userAnswer.trim().equals(correctAnswer, ignoreCase = true)

                        if (isCorrect) {
                            score += 1
                        }
                    } else {
                        // Move to next question
                        if (currentSentenceIndex < sentences.size - 1) {
                            currentSentenceIndex++
                            userAnswer = ""
                            isAnswerChecked = false
                        } else {
                            showResults = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(
                    text = if (!isAnswerChecked) "Check Answer" else "Continue",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ResultsView(
    score: Int,
    totalQuestions: Int,
    onPlayAgain: () -> Unit
) {
    val percentage = (score.toFloat() / totalQuestions.toFloat() * 100).toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Game Completed!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "$score/$totalQuestions",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "$percentage%",
            fontSize = 24.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = when {
                percentage >= 90 -> "Excellent! Great job!"
                percentage >= 70 -> "Good work! Keep practicing!"
                percentage >= 50 -> "Nice effort! You're improving!"
                else -> "Keep practicing! You'll get better!"
            },
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onPlayAgain,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Play Again"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Play Again",
                fontSize = 16.sp
            )
        }
    }
}