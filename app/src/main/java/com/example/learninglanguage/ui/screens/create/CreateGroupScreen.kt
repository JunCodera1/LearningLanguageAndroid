package com.example.learninglanguage.ui.screens.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroupScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val groupName = remember { mutableStateOf("") }
    val groupDescription = remember { mutableStateOf("") }
    val groupLanguage = remember { mutableStateOf("") }
    val groupLevel = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Group") },
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
                text = "Create a New Language Group",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Group Name
            OutlinedTextField(
                value = groupName.value,
                onValueChange = { groupName.value = it },
                label = { Text("Group Name") },
                placeholder = { Text("Enter group name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Language Selection
            OutlinedTextField(
                value = groupLanguage.value,
                onValueChange = { groupLanguage.value = it },
                label = { Text("Language") },
                placeholder = { Text("e.g. Spanish, French, Japanese") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Level Selection
            OutlinedTextField(
                value = groupLevel.value,
                onValueChange = { groupLevel.value = it },
                label = { Text("Level") },
                placeholder = { Text("e.g. Beginner, Intermediate, Advanced") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            OutlinedTextField(
                value = groupDescription.value,
                onValueChange = { groupDescription.value = it },
                label = { Text("Description") },
                placeholder = { Text("Describe your group...") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Group Image Button
            Button(
                onClick = { /* Handle image selection */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = "Add Group Image"
                )
                Text(
                    text = "Add Group Image",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Create Group Button
            Button(
                onClick = {
                    // Handle group creation logic here
                    // Then navigate back or to the new group
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = groupName.value.isNotBlank() && groupLanguage.value.isNotBlank()
            ) {
                Text("Create Group")
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}