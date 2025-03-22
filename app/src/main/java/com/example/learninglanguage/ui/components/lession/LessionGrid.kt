package com.example.learninglanguage.ui.components.lession

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Lesson(val title: String, val icon: ImageVector, val route: String, val color: Color)

@Composable
fun LessonGrid(navController: NavController) {
    val lessons = listOf(
        Lesson("Basics 1", Icons.Filled.School, "lesson_basics", Color(0xFF4CAF50)),
        Lesson("Phrases", Icons.Filled.Chat, "lesson_phrases", Color(0xFF2196F3)),
        Lesson("Animals", Icons.Filled.Pets, "lesson_animals", Color(0xFFFF9800)),
        Lesson("Food", Icons.Filled.Fastfood, "lesson_food", Color(0xFFF44336)),
        Lesson("Family", Icons.Filled.FamilyRestroom, "lesson_family", Color(0xFF9C27B0))
    )

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp), // Sắp xếp tự động theo kích thước màn hình
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(lessons) { lesson ->
            LessonItem(lesson) {
                navController.navigate(lesson.route)
            }
        }
    }
}

@Composable
fun LessonItem(lesson: Lesson, onClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.95f else 1f, label = "")

    Card(
        colors = CardDefaults.cardColors(containerColor = lesson.color),
        modifier = Modifier
            .size(150.dp)
            .scale(scale)
            .clickable {
                isPressed = true
                onClick()
                isPressed = false
            },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = lesson.icon,
                contentDescription = lesson.title,
                modifier = Modifier.size(48.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = lesson.title,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}
