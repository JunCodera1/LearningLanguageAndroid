package com.example.learninglanguage.ui.screens.topicScreens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.learninglanguage.data.model.VocabularyTopic
import com.example.learninglanguage.ui.contents.topic.TopicDetailContent
import com.example.learninglanguage.ui.contents.topic.WordListDrawerContents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicDetailScreen(
    selectedTopic: VocabularyTopic,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    onBackPressed: () -> Unit
) {
    DismissibleNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            WordListDrawerContents(words = selectedTopic.words)
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(selectedTopic.name) },
                        navigationIcon = {
                            IconButton(onClick = onBackPressed) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.List,
                                    contentDescription = "Open Word List"
                                )
                            }
                        }
                    )
                }
            ) { paddingValues ->
                TopicDetailContent(
                    topic = selectedTopic,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    )
}