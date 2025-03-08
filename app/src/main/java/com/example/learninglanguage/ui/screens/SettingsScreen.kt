package com.example.learninglanguage.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BrightnessMedium
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learninglanguage.ui.theme.GreenJC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("English") }
    var learningGoal by remember { mutableStateOf("30 mins/day") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings", fontSize = 22.sp, color = GreenJC) })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            SettingItem(icon = Icons.Filled.AccountCircle, title = "Edit Profile") {
                // TODO: Navigate to profile editing screen
            }

            SettingItem(icon = Icons.Filled.Flag, title = "Language: $selectedLanguage") {
                selectedLanguage = if (selectedLanguage == "English") "Vietnamese" else "English"
            }

            SettingItem(icon = Icons.Filled.BrightnessMedium, title = "Dark Mode: ${if (isDarkMode) "On" else "Off"}") {
                isDarkMode = !isDarkMode
            }

            SettingItem(icon = Icons.Filled.Notifications, title = "Notification Settings") {
                // TODO: Open notification settings
            }

            SettingItem(icon = Icons.Filled.TrackChanges, title = "Learning Goal: $learningGoal") {
                learningGoal = if (learningGoal == "30 mins/day") "1 hour/day" else "30 mins/day"
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* TODO: Handle logout */ },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Filled.Logout, contentDescription = "Logout", tint = MaterialTheme.colorScheme.onError)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Logout")
            }
        }
    }
}

@Composable
fun SettingItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = title, tint = GreenJC)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = title, fontSize = 18.sp)
        }
    }
}
