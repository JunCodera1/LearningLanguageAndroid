package com.example.learninglanguage.ui.components.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learninglanguage.ui.screens.learning.GameType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameSelectorView(onGameSelected: (GameType) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Learning Games",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ElevatedCard(
                    onClick = { onGameSelected(GameType.QUIZ) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Quiz,
                            contentDescription = "Quiz",
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Quick Quiz",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Test your knowledge with multiple choice questions",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            item {
                ElevatedCard(
                    onClick = { onGameSelected(GameType.WORD_MATCHING) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Compare,
                            contentDescription = "Word Matching",
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Word Matching",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Match words with their correct translations",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            item {
                ElevatedCard(
                    onClick = { onGameSelected(GameType.FILL_BLANKS) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.TextFields,
                            contentDescription = "Fill Blanks",
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.tertiary
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Fill in the Blanks",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Complete sentences by filling in missing words",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}