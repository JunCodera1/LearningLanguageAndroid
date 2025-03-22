package com.example.learninglanguage.data.model

import java.util.UUID

data class Question(
    val id: UUID,
    val quizId: UUID,
    val text: String
)