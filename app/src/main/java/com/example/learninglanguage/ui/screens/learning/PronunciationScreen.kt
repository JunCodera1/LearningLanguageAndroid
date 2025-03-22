package com.example.learninglanguage.ui.screens.learning

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
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learninglanguage.data.model.PronunciationItem
import com.example.learninglanguage.ui.components.button.PronunciationNavigationButtons
import com.example.learninglanguage.ui.components.card.PronunciationPracticeCard
import com.example.learninglanguage.ui.components.card.PronunciationWordCard
import com.example.learninglanguage.ui.components.dialog.PronunciationResultDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.SecureRandom

@Composable
fun PronunciationScreen() {
    val pronunciationItems = remember { getSamplePronunciationItems() }
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
        ProgressHeader(
            currentIndex = currentIndex,
            totalItems = pronunciationItems.size
        )

        // Word card
        PronunciationWordCard(currentItem = currentItem)

        Spacer(modifier = Modifier.height(24.dp))

        // Audio player section
        PronunciationPracticeCard(
            isPlaying = isPlaying,
            isRecording = isRecording,
            playbackProgress = playbackProgress,
            recordingAmplitude = recordingAmplitude,
            onPlayToggle = {
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
            onRecordToggle = {
                isRecording = !isRecording
                if (isRecording) {
                    // Simulate recording
                    coroutineScope.launch {
                        val secureRandom = SecureRandom()
                        while (isRecording) {
                            delay(100)
                            recordingAmplitude = (0.2 + secureRandom.nextDouble() * 0.5).toFloat()
                        }
                    }
                } else if (recordingAmplitude > 0) {
                    // Finished recording
                    showComparisonDialog = true
                    pronunciationScore = (70..95).random() // Simulate a score
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        // Navigation buttons
        PronunciationNavigationButtons(
            currentIndex = currentIndex,
            totalItems = pronunciationItems.size,
            onPrevious = {
                if (currentIndex > 0) {
                    currentIndex--
                    playbackProgress = 0f
                    isPlaying = false
                    isRecording = false
                }
            },
            onNext = {
                if (currentIndex < pronunciationItems.size - 1) {
                    currentIndex++
                    playbackProgress = 0f
                    isPlaying = false
                    isRecording = false
                }
            }
        )
    }

    // Pronunciation comparison dialog
    if (showComparisonDialog) {
        PronunciationResultDialog(
            score = pronunciationScore,
            onDismiss = {
                showComparisonDialog = false
                recordingAmplitude = 0f
            },
            onTryAgain = {
                showComparisonDialog = false
                recordingAmplitude = 0f
                isRecording = true
                // Simulate recording again
                coroutineScope.launch {
                    val secureRandom = SecureRandom()
                    while (isRecording) {
                        delay(100)
                        recordingAmplitude = (secureRandom.nextDouble() * 0.5 + 0.2).toFloat()
                    }
                }
            }
        )
    }
}

@Composable
fun ProgressHeader(currentIndex: Int, totalItems: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = (currentIndex + 1).toFloat() / totalItems,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Text(
            text = "Word ${currentIndex + 1}/$totalItems",
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun AudioVisualizer(amplitude: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until 10) {
            val barHeight = remember(amplitude, i) {
                (amplitude * (0.5 + Math.random() * 0.5) * 32).dp
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

// Sample data function
fun getSamplePronunciationItems(): List<PronunciationItem> {
    return listOf(
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