package com.example.learninglanguage.ui.screens.topic

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.learninglanguage.data.model.VocabularyTopic
import com.example.learninglanguage.data.model.WordItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicBasedLearningScreen() {
    val sampleWords = getSampleWords()
    val topics = getSampleTopics(sampleWords)

    var selectedTopic by remember { mutableStateOf<VocabularyTopic?>(null) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    if (selectedTopic == null) {
        TopicSelectionScreen(topics) { topic -> selectedTopic = topic }
    } else {
        TopicDetailScreen(
            selectedTopic = selectedTopic!!,
            drawerState = drawerState,
            coroutineScope = coroutineScope,
            onBackPressed = { selectedTopic = null }
        )
    }
}

// Helper functions to create sample data
private fun getSampleWords(): List<WordItem> {
    return listOf(
        WordItem(
            original = "Airport",
            translation = "Sân bay",
            phonetic = "/ˈeə.pɔːt/",
            example = "We arrived at the airport two hours early."
        ),
        WordItem(
            original = "Hotel",
            translation = "Khách sạn",
            phonetic = "/həʊˈtel/",
            example = "We stayed at a five-star hotel."
        ),
        WordItem(
            original = "Passport",
            translation = "Hộ chiếu",
            phonetic = "/ˈpɑːs.pɔːt/",
            example = "Don't forget to bring your passport."
        ),
        WordItem(
            original = "Suitcase",
            translation = "Vali",
            phonetic = "/ˈsuːt.keɪs/",
            example = "I packed my suitcase the night before."
        ),
        WordItem(
            original = "Ticket",
            translation = "Vé",
            phonetic = "/ˈtɪk.ɪt/",
            example = "You can book tickets online."
        )
    )
}

private fun getSampleTopics(sampleWords: List<WordItem>): List<VocabularyTopic> {
    return listOf(
        VocabularyTopic(
            id = "travel",
            name = "Du lịch",
            description = "Từ vựng cần thiết khi đi du lịch",
            wordsCount = 25,
            icon = Icons.Default.FlightTakeoff,
            accentColor = Color(0xFF2196F3),
            words = sampleWords
        )
    )
}