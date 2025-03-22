package com.example.learninglanguage.ui.contents.topic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learninglanguage.data.model.VocabularyTopic
import com.example.learninglanguage.ui.components.button.ActionButton
import com.example.learninglanguage.ui.components.card.FeatureCard
import com.example.learninglanguage.ui.components.topic.ProgressStats
import com.example.learninglanguage.ui.components.topic.TopicHeader


@Composable
fun TopicDetailContent(
    topic: VocabularyTopic,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopicHeader(topic)

        // Learning Section
        FeatureCard(
            title = "Học từ vựng",
            content = {
                ActionButton(
                    text = "Flashcards",
                    icon = Icons.Default.School,
                    color = topic.accentColor,
                    onClick = { /* TODO: Implement flashcard feature */ }
                )
            }
        )

        // Testing Section
        FeatureCard(
            title = "Kiểm tra",
            content = {
                ActionButton(
                    text = "Trắc nghiệm",
                    icon = Icons.Default.QuestionAnswer,
                    color = topic.accentColor,
                    onClick = { /* TODO: Implement quiz feature */ }
                )

                Spacer(modifier = Modifier.height(8.dp))

                ActionButton(
                    text = "Trò chơi ghép từ",
                    icon = Icons.Default.Extension,
                    color = topic.accentColor,
                    onClick = { /* TODO: Implement matching game */ }
                )
            }
        )

        // Statistics Section
        FeatureCard(
            title = "Thống kê",
            content = {
                ProgressStats(
                    progress = 0.35f,
                    color = topic.accentColor
                )
            }
        )
    }
}