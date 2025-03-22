package com.example.learninglanguage.data.model

data class WordData(
    val word: String,
    val translation: String,
    val example: String,
    val options: List<String> = emptyList()
)