package com.example.learninglanguage.ui.screens.createScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguage.ui.theme.GreenJC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateQuestionScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val questionTitle = remember { mutableStateOf("") }
    val questionBody = remember { mutableStateOf("") }
    val selectedLanguage = remember { mutableStateOf("") }
    val isMultipleChoice = remember { mutableStateOf(false) }

    // For multiple choice options
    val options = remember { mutableStateListOf("", "") }
    val correctAnswerIndex = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Question") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Ask the Community",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Question Title
            OutlinedTextField(
                value = questionTitle.value,
                onValueChange = { questionTitle.value = it },
                label = { Text("Question Title") },
                placeholder = { Text("Enter your question title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Language Selection
            OutlinedTextField(
                value = selectedLanguage.value,
                onValueChange = { selectedLanguage.value = it },
                label = { Text("Language") },
                placeholder = { Text("Select the language your question is about") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Question Body
            OutlinedTextField(
                value = questionBody.value,
                onValueChange = { questionBody.value = it },
                label = { Text("Question Details") },
                placeholder = { Text("Provide more context or details about your question...") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Multiple Choice Option
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isMultipleChoice.value,
                    onCheckedChange = { isMultipleChoice.value = it }
                )

                Text(
                    text = "Make this a multiple choice question",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Multiple Choice Questions Section
            if (isMultipleChoice.value) {
                Spacer(modifier = Modifier.height(16.dp))

                Divider()

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Answer Options",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(8.dp))

                options.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        // Radio button to select the correct answer
                        IconButton(
                            onClick = { correctAnswerIndex.value = index },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Icon(
                                imageVector = if (correctAnswerIndex.value == index)
                                    Icons.Default.Check else Icons.Default.Close,
                                contentDescription = "Correct answer",
                                tint = if (correctAnswerIndex.value == index)
                                    GreenJC else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }

                        OutlinedTextField(
                            value = option,
                            onValueChange = { options[index] = it },
                            label = { Text("Option ${index + 1}") },
                            modifier = Modifier.weight(1f)
                        )

                        if (options.size > 2) {
                            IconButton(
                                onClick = {
                                    options.removeAt(index)
                                    if (correctAnswerIndex.value >= options.size) {
                                        correctAnswerIndex.value = options.size - 1
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Remove option"
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Add option button
                Button(
                    onClick = { options.add("") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Another Option")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Submit Button
            Button(
                onClick = {
                    // Handle question submission logic here
                    // Then navigate back or to the question feed
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = questionTitle.value.isNotBlank() && (
                        !isMultipleChoice.value ||
                                options.all { it.isNotBlank() }
                        )
            ) {
                Text("Post Question")
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}