package com.example.learninglanguage.ui.contents.topic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learninglanguage.data.model.WordItem
import com.example.learninglanguage.ui.components.topic.WordCard
import kotlin.collections.forEach

@Composable
fun WordListDrawerContents(words: List<WordItem>) {
    DismissibleDrawerSheet {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .padding(16.dp)
        ) {
            Text(
                text = "Danh sách từ vựng",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            words.forEach { word ->
                WordCard(word = word)
            }
        }
    }
}