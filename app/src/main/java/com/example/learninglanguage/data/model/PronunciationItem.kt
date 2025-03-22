package com.example.learninglanguage.data.model

data class PronunciationItem(
    val word: String,
    val translation: String,
    val phonetic: String,
    val example: String,
    val audioUrl: String
)