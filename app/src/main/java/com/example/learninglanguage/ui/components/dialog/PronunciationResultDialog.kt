package com.example.learninglanguage.ui.components.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PronunciationResultDialog(
    score: Int,
    onDismiss: () -> Unit,
    onTryAgain: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Pronunciation Analysis") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Your pronunciation score:")

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "$score%",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = when {
                        score >= 90 -> MaterialTheme.colorScheme.primary
                        score >= 70 -> MaterialTheme.colorScheme.tertiary
                        else -> MaterialTheme.colorScheme.error
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = when {
                        score >= 90 -> "Excellent! Your pronunciation is very close to native speakers."
                        score >= 70 -> "Good job! Keep practicing to improve further."
                        else -> "More practice needed. Listen carefully and try again."
                    },
                    textAlign = TextAlign.Center
                )
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onTryAgain) {
                Text("Try Again")
            }
        }
    )
}