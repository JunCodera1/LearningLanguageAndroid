package com.example.learninglanguage.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguage.Screens
import com.example.learninglanguage.ui.theme.GreenJC
import com.example.learninglanguage.viewmodel.AuthViewModel

@Composable
fun BottomNavigation(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val selected = remember { mutableStateOf(Icons.Default.Home) }

    BottomAppBar(containerColor = GreenJC) {
        IconButton(onClick = {
            selected.value = Icons.Default.Home
            navController.navigate(Screens.Home.screen) {
                popUpTo(Screens.Home.screen) { inclusive = false }
            }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Home, contentDescription = "Home", Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.Home) Color.White else Color.DarkGray)
        }

        IconButton(onClick = {
            selected.value = Icons.Default.Search
            navController.navigate(Screens.Search.screen) {
                popUpTo(Screens.Home.screen) { inclusive = false }
            }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Search, contentDescription = "Search", Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.Search) Color.White else Color.DarkGray)
        }

        IconButton(onClick = {
            selected.value = Icons.Default.Notifications
            navController.navigate(Screens.Notification.screen) {
                popUpTo(Screens.Home.screen) { inclusive = false }
            }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Notifications, contentDescription = "Notifications", Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.Notifications) Color.White else Color.DarkGray)
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            FloatingActionButton(onClick = {
                Toast.makeText(context, "Open bottom sheet", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = GreenJC)
            }
        }
    }
}

