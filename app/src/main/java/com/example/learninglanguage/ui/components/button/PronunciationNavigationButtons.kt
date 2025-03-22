package com.example.learninglanguage.ui.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PronunciationNavigationButtons(
    currentIndex: Int,
    totalItems: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPrevious,
            enabled = currentIndex > 0
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Previous")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Previous")
        }

        Button(
            onClick = onNext,
            enabled = currentIndex < totalItems - 1
        ) {
            Text("Next")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.ArrowForward, contentDescription = "Next")
        }
    }
}