package com.example.learninglanguage.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class FlashCard(
    val frontText: String,
    val backText: String,
    val example: String = ""
)

@Composable
fun FlashCardScreen() {
    // Sample data - replace with your actual data source
    val flashCards = remember {
        listOf(
            FlashCard("Hello", "Xin chào", "Hello, how are you?"),
            FlashCard("Thank you", "Cảm ơn", "Thank you for your help."),
            FlashCard("Goodbye", "Tạm biệt", "Goodbye, see you tomorrow."),
            FlashCard("Yes", "Có", "Yes, I understand."),
            FlashCard("No", "Không", "No, I don't want that.")
        )
    }

    var currentCardIndex by remember { mutableStateOf(0) }
    var isFlipped by remember { mutableStateOf(false) }
    var knowCount by remember { mutableStateOf(0) }
    var unknownCount by remember { mutableStateOf(0) }

    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        label = "card_flip"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Progress indicator
        LinearProgressIndicator(
            progress = (currentCardIndex + 1).toFloat() / flashCards.size,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Text(
            text = "Card ${currentCardIndex + 1}/${flashCards.size}",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Known: $knowCount")
            Text("Unknown: $unknownCount")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Flash card
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(16.dp))
                .clickable { isFlipped = !isFlipped }
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12f * density
                },
            contentAlignment = Alignment.Center
        ) {
            // Front of card
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(24.dp)
                    .graphicsLayer {
                        rotationY = if (isFlipped) 180f else 0f
                        alpha = if (isFlipped) 0f else 1f
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = flashCards[currentCardIndex].frontText,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Tap to flip",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    )
                }
            }

            // Back of card
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(24.dp)
                    .graphicsLayer {
                        rotationY = if (isFlipped) 0f else 180f
                        alpha = if (isFlipped) 1f else 0f
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = flashCards[currentCardIndex].backText,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = flashCards[currentCardIndex].example,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Tap to flip back",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.7f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Control buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Previous button
            Button(
                onClick = {
                    if (currentCardIndex > 0) {
                        currentCardIndex--
                        isFlipped = false
                    }
                },
                enabled = currentCardIndex > 0
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Previous")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Previous")
            }

            // Next button
            Button(
                onClick = {
                    if (currentCardIndex < flashCards.size - 1) {
                        currentCardIndex++
                        isFlipped = false
                    }
                },
                enabled = currentCardIndex < flashCards.size - 1
            ) {
                Text("Next")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = "Next")
            }
        }

        // Know/Don't know buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    unknownCount++
                    if (currentCardIndex < flashCards.size - 1) {
                        currentCardIndex++
                        isFlipped = false
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Don't Know")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Don't Know")
            }

            Button(
                onClick = {
                    knowCount++
                    if (currentCardIndex < flashCards.size - 1) {
                        currentCardIndex++
                        isFlipped = false
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Default.Check, contentDescription = "Know")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Know")
            }
        }
    }
}