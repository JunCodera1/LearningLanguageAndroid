package com.example.learninglanguage.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class VocabularyTopic(
    val id: String,
    val name: String,
    val description: String,
    val wordsCount: Int,
    val icon: ImageVector,
    val accentColor: Color,
    val words: List<WordItem>
)

