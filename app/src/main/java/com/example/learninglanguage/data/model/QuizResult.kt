package com.example.learninglanguage.data.model

import java.util.UUID

data class QuizResult(
    val userId: UUID,
    val quizId: UUID,
    val score: Int
)