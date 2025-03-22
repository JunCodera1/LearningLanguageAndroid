package com.example.learninglanguage.ui.components.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.collections.plus

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