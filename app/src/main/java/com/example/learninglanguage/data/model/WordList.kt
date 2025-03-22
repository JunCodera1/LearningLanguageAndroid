package com.example.learninglanguage.data.model

import java.util.UUID

data class WordList(
    val topicId: UUID,
    val words: List<Word>
)