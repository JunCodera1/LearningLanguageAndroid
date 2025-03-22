package com.example.learninglanguage.ui.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learninglanguage.ui.components.section.PronunciationAudioPlaybackSection
import com.example.learninglanguage.ui.components.section.PronunciationAudioRecordingSection

@Composable
fun PronunciationPracticeCard(
    isPlaying: Boolean,
    isRecording: Boolean,
    playbackProgress: Float,
    recordingAmplitude: Float,
    onPlayToggle: () -> Unit,
    onRecordToggle: () -> Unit
) {
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
            // Audio playback section
            PronunciationAudioPlaybackSection(
                isPlaying = isPlaying,
                playbackProgress = playbackProgress,
                onPlayToggle = onPlayToggle
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Recording section
            PronunciationAudioRecordingSection(
                isRecording = isRecording,
                recordingAmplitude = recordingAmplitude,
                onRecordToggle = onRecordToggle
            )
        }
    }
}