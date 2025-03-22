package com.example.learninglanguage.data.repository

import com.example.learninglanguage.data.model.Topic
import java.util.UUID

interface TopicRepository {
    suspend fun createTopic(topic: Topic): Topic
    suspend fun findTopicById(id: UUID): Topic?
    suspend fun updateTopic(topic: Topic): Topic
}