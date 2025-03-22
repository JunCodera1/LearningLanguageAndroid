package com.example.learninglanguage.data.model

import java.util.UUID

data class Quiz(
    val id: UUID,
    val name: String,
    val topicId: UUID? = null
)