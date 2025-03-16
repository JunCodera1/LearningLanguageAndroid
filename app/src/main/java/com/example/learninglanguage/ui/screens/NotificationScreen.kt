package com.example.learninglanguage.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learninglanguage.ui.theme.GreenJC
import com.example.learninglanguage.viewmodel.AuthViewModel

data class Notification(val title: String, val message: String, val time: String, val type: NotificationType)

enum class NotificationType {
    ACHIEVEMENT, REMINDER, LESSON
}

@Composable
fun NotificationScreen(navController: NavController, authViewModel: AuthViewModel) {
    val notifications = remember {
        mutableStateListOf(
            Notification("Lesson Completed!", "Congratulations! You have completed the 'Basic Grammar' lesson.", "5 minutes ago", NotificationType.ACHIEVEMENT),
            Notification("Daily Reminder", "Don't forget to review today's vocabulary to maintain your streak!", "2 hours ago", NotificationType.REMINDER),
            Notification("New Lesson Available", "Discover the 'Common Idioms' lesson now!", "Yesterday", NotificationType.LESSON)
        )
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
            Text(
                text = "Notifications",
                fontSize = 26.sp,
                color = GreenJC,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(notifications) { notification ->
                    NotificationCard(notification)
                }
            }
        }
    }
}

@Composable
fun NotificationCard(notification: Notification) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            NotificationIcon(notification.type)

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(text = notification.title, fontSize = 18.sp, color = GreenJC)
                Text(text = notification.message, fontSize = 14.sp)
                Text(text = notification.time, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
            }
        }
    }
}

@Composable
fun NotificationIcon(type: NotificationType) {
    when (type) {
        NotificationType.ACHIEVEMENT -> Icon(Icons.Filled.Star, contentDescription = "Achievement", tint = GreenJC)
        NotificationType.REMINDER -> Icon(Icons.Filled.Notifications, contentDescription = "Reminder", tint = MaterialTheme.colorScheme.primary)
        NotificationType.LESSON -> Icon(Icons.Filled.CheckCircle, contentDescription = "Lesson", tint = MaterialTheme.colorScheme.secondary)
    }
}
