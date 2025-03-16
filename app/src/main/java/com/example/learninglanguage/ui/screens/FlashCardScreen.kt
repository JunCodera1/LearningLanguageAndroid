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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Progress and stats
        FlashCardProgress(
            currentIndex = currentCardIndex,
            totalCards = flashCards.size,
            knowCount = knowCount,
            unknownCount = unknownCount
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Flash card
        FlashCard(
            card = flashCards[currentCardIndex],
            isFlipped = isFlipped,
            onFlip = { isFlipped = !isFlipped }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Navigation and feedback controls
        CardControls(
            currentIndex = currentCardIndex,
            totalCards = flashCards.size,
            onPrevious = {
                if (currentCardIndex > 0) {
                    currentCardIndex--
                    isFlipped = false
                }
            },
            onNext = { navigateToNextCard(currentCardIndex, flashCards.size) { currentCardIndex++; isFlipped = false } },
            onKnow = {
                knowCount++
                navigateToNextCard(currentCardIndex, flashCards.size) { currentCardIndex++; isFlipped = false }
            },
            onDontKnow = {
                unknownCount++
                navigateToNextCard(currentCardIndex, flashCards.size) { currentCardIndex++; isFlipped = false }
            }
        )
    }
}

@Composable
fun FlashCardProgress(
    currentIndex: Int,
    totalCards: Int,
    knowCount: Int,
    unknownCount: Int
) {
    LinearProgressIndicator(
        progress = (currentIndex + 1).toFloat() / totalCards,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )

    Text(
        text = "Card ${currentIndex + 1}/$totalCards",
        modifier = Modifier.padding(bottom = 16.dp)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Known: $knowCount")
        Text("Unknown: $unknownCount")
    }
}

@Composable
fun FlashCard(
    card: FlashCard,
    isFlipped: Boolean,
    onFlip: () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        label = "card_flip"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .aspectRatio(1.5f)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onFlip)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            },
        contentAlignment = Alignment.Center
    ) {
        CardFront(
            text = card.frontText,
            isVisible = !isFlipped
        )

        CardBack(
            translation = card.backText,
            example = card.example,
            isVisible = isFlipped
        )
    }
}

@Composable
fun CardFront(text: String, isVisible: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(24.dp)
            .graphicsLayer {
                rotationY = if (!isVisible) 180f else 0f
                alpha = if (isVisible) 1f else 0f
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
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
}

@Composable
fun CardBack(translation: String, example: String, isVisible: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(24.dp)
            .graphicsLayer {
                rotationY = if (isVisible) 0f else 180f
                alpha = if (isVisible) 1f else 0f
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = translation,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = example,
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

@Composable
fun CardControls(
    currentIndex: Int,
    totalCards: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onKnow: () -> Unit,
    onDontKnow: () -> Unit
) {
    // Navigation buttons
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
            enabled = currentIndex < totalCards - 1
        ) {
            Text("Next")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.ArrowForward, contentDescription = "Next")
        }
    }

    // Knowledge feedback buttons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onDontKnow,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Don't Know")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Don't Know")
        }

        Button(
            onClick = onKnow,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Icon(Icons.Default.Check, contentDescription = "Know")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Know")
        }
    }
}

// Utility function to navigate to next card if available
private fun navigateToNextCard(currentIndex: Int, totalCards: Int, navigate: () -> Unit) {
    if (currentIndex < totalCards - 1) {
        navigate()
    }
}