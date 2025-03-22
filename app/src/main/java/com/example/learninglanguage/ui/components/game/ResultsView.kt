package com.example.learninglanguage.ui.components.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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