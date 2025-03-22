package com.example.learninglanguage.data.model

import java.util.UUID

data class Word(
    val id: UUID,
    val topicId: UUID,
    val text: String,
    val translation: String,
    val example: String? = null,
    val audioUrl: String? = null
)
