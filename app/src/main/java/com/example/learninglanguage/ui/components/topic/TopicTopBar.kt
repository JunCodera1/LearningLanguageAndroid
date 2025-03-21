package com.example.learninglanguage.ui.components.topic

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.learninglanguage.data.model.VocabularyTopic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicTopBar(
    coroutineScope: CoroutineScope,
    selectedTopic: MutableState<VocabularyTopic?>,
    drawerState: DrawerState
) {
    TopAppBar(
        title = {
            Text(text = selectedTopic.value?.name ?: "Chọn chủ đề")
        },
        navigationIcon = {
            IconButton(onClick = { selectedTopic.value = null }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.List,
                    contentDescription = "Danh sách từ vựng"
                )
            }
        }
    )
}