package com.example.learninglanguage.ui.components.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learninglanguage.ui.screens.learning.AudioVisualizer

@Composable
fun PronunciationAudioRecordingSection(
    isRecording: Boolean,
    recordingAmplitude: Float,
    onRecordToggle: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Record Your Pronunciation",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Recording button
        IconButton(
            onClick = onRecordToggle,
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

        // Audio visualizer
        if (isRecording) {
            AudioVisualizer(amplitude = recordingAmplitude)
        }
    }
}