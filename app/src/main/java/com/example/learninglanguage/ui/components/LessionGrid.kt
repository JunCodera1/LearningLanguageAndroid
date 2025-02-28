package com.example.learninglanguage.ui.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LessonGrid(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LessonItem(title = "Basics 1", icon = Icons.Filled.School)
        LessonItem(title = "Phrases", icon = Icons.Filled.Chat)
        LessonItem(title = "Animals", icon = Icons.Filled.Pets)
        LessonItem(title = "Food", icon = Icons.Filled.Fastfood)
        LessonItem(title = "Family", icon = Icons.Filled.FamilyRestroom)
    }
}
