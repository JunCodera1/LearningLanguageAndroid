package com.example.learninglanguage.ui.screens.learning

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learninglanguage.ui.components.game.FillBlanksGame
import com.example.learninglanguage.ui.components.game.GameSelectorView
import com.example.learninglanguage.ui.components.game.QuizGame
import com.example.learninglanguage.ui.components.game.WordMatchingGame

enum class GameType {
    QUIZ,
    WORD_MATCHING,
    FILL_BLANKS
}



@Composable
fun LearningGamesScreen() {
    var currentGameType by remember { mutableStateOf(GameType.QUIZ) }
    var showGameSelector by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (showGameSelector) {
            GameSelectorView(
                onGameSelected = { gameType ->
                    currentGameType = gameType
                    showGameSelector = false
                }
            )
        } else {
            // Show back button
            IconButton(
                onClick = {
                    showGameSelector = true
                },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Game Selection"
                )
            }

            // Show selected game
            when (currentGameType) {
                GameType.QUIZ -> QuizGame()
                GameType.WORD_MATCHING -> WordMatchingGame()
                GameType.FILL_BLANKS -> FillBlanksGame()
            }
        }
    }
}