package com.example.learninglanguage.ui.components.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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