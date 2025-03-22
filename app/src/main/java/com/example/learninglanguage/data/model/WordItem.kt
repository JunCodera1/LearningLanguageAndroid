package com.example.learninglanguage.data.model

data class WordItem(
    val original: String,
    val translation: String,
    val phonetic: String = "",
    val example: String = ""
)