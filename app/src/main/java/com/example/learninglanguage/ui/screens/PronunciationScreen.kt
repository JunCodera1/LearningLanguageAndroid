package com.example.learninglanguage.ui.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.SecureRandom

data class PronunciationItem(
    val word: String,
    val translation: String,
    val phonetic: String,
    val example: String,
    val audioUrl: String
)

@Composable
fun PronunciationScreen() {
    val secureRandom = SecureRandom()
    val pronunciationItems = remember {
        listOf(
            PronunciationItem(
                word = "Hello",
                translation = "Xin chào",
                phonetic = "/həˈloʊ/",
                example = "Hello, nice to meet you.",
                audioUrl = "hello_audio.mp3"
            ),
            PronunciationItem(
                word = "Goodbye",
                translation = "Tạm biệt",
                phonetic = "/ˌɡʊdˈbaɪ/",
                example = "Goodbye, see you tomorrow.",
                audioUrl = "goodbye_audio.mp3"
            ),
            PronunciationItem(
                word = "Thank you",
                translation = "Cảm ơn",
                phonetic = "/ˈθæŋk ju/",
                example = "Thank you for your help.",
                audioUrl = "thank_you_audio.mp3"
            ),
            PronunciationItem(
                word = "Excuse me",
                translation = "Xin lỗi",
                phonetic = "/ɪkˈskjuz mi/",
                example = "Excuse me, could you help me?",
                audioUrl = "excuse_me_audio.mp3"
            ),
            PronunciationItem(
                word = "How are you",
                translation = "Bạn khỏe không",
                phonetic = "/haʊ ɑr ju/",
                example = "How are you doing today?",
                audioUrl = "how_are_you_audio.mp3"
            )
        )
    }

    var currentIndex by remember { mutableStateOf(0) }
    var isPlaying by remember { mutableStateOf(false) }
    var isRecording by remember { mutableStateOf(false) }
    var playbackProgress by remember { mutableStateOf(0f) }
    var recordingAmplitude by remember { mutableStateOf(0f) }
    var showComparisonDialog by remember { mutableStateOf(false) }
    var pronunciationScore by remember { mutableStateOf(0) }

    val currentItem = pronunciationItems[currentIndex]
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar with progress
        LinearProgressIndicator(
            progress = (currentIndex + 1).toFloat() / pronunciationItems.size,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Text(
            text = "Word ${currentIndex + 1}/${pronunciationItems.size}",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Word card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = currentItem.word,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = currentItem.phonetic,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = currentItem.translation,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "\"${currentItem.example}\"",
                    fontSize = 18.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Audio player section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Listen and Practice",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Play button and progress
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {
                            isPlaying = !isPlaying
                            if (isPlaying) {
                                // Simulate audio playback
                                coroutineScope.launch {
                                    playbackProgress = 0f
                                    while (playbackProgress < 1f && isPlaying) {
                                        delay(100)
                                        playbackProgress += 0.05f
                                        if (playbackProgress >= 1f) {
                                            isPlaying = false
                                            playbackProgress = 0f
                                        }
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .size(64.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape)
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (isPlaying) {
                        LinearProgressIndicator(
                            progress = playbackProgress,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .padding(vertical = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Record Your Pronunciation",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Recording button and visualizer
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {
                            isRecording = !isRecording
                            if (isRecording) {
                                // Simulate recording
                                coroutineScope.launch {
                                    while (isRecording) {
                                        delay(100)
                                        recordingAmplitude = (0.2 + secureRandom.nextDouble() * 0.5).toFloat()                                    }
                                }
                            } else if (recordingAmplitude > 0) {
                                // Finished recording
                                showComparisonDialog = true
                                pronunciationScore = (70..95).random() // Simulate a score
                            }
                        },
                        modifier = Modifier
                            .size(64.dp)
                            .background(
                                if (isRecording) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary,
                                CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = if (isRecording) "Stop Recording" else "Start Recording",
                            tint = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Simple audio visualizer
                    if (isRecording) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(32.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            for (i in 0 until 10) {
                                val barHeight = remember(recordingAmplitude, i) {
                                    (recordingAmplitude * (0.5 + Math.random() * 0.5) * 32).dp
                                }

                                Box(
                                    modifier = Modifier
                                        .width(6.dp)
                                        .height(barHeight)
                                        .clip(RoundedCornerShape(3.dp))
                                        .background(MaterialTheme.colorScheme.error)
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Navigation buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentIndex > 0) {
                        currentIndex--
                        playbackProgress = 0f
                        isPlaying = false
                        isRecording = false
                    }
                },
                enabled = currentIndex > 0
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Previous")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Previous")
            }

            Button(
                onClick = {
                    if (currentIndex < pronunciationItems.size - 1) {
                        currentIndex++
                        playbackProgress = 0f
                        isPlaying = false
                        isRecording = false
                    }
                },
                enabled = currentIndex < pronunciationItems.size - 1
            ) {
                Text("Next")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = "Next")
            }
        }
    }

    // Pronunciation comparison dialog
    if (showComparisonDialog) {
        AlertDialog(
            onDismissRequest = {
                showComparisonDialog = false
                recordingAmplitude = 0f
            },
            title = { Text("Pronunciation Analysis") },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Your pronunciation score:")

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "$pronunciationScore%",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = when {
                            pronunciationScore >= 90 -> MaterialTheme.colorScheme.primary
                            pronunciationScore >= 70 -> MaterialTheme.colorScheme.tertiary
                            else -> MaterialTheme.colorScheme.error
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = when {
                            pronunciationScore >= 90 -> "Excellent! Your pronunciation is very close to native speakers."
                            pronunciationScore >= 70 -> "Good job! Keep practicing to improve further."
                            else -> "More practice needed. Listen carefully and try again."
                        },
                        textAlign = TextAlign.Center
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showComparisonDialog = false
                        recordingAmplitude = 0f
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        showComparisonDialog = false
                        recordingAmplitude = 0f
                        isRecording = true
                        // Simulate recording again
                        coroutineScope.launch {
                            while (isRecording) {
                                delay(100)
                                recordingAmplitude = (Math.random() * 0.5 + 0.2).toFloat()
                            }
                        }
                    }
                ) {
                    Text("Try Again")
                }
            }
        )
    }
}