package com.example.learninglanguage.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguage.Screens
import com.example.learninglanguage.ui.theme.GreenJC
import com.example.learninglanguage.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val selected = remember { mutableStateOf(Icons.Default.Home) }

    // Add states for bottom sheet
    val showBottomSheet = remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

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
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            FloatingActionButton(onClick = {
                Toast.makeText(context, "Opening create options", Toast.LENGTH_SHORT).show()
                showBottomSheet.value = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = GreenJC)
            }
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
        IconButton(onClick = {
            selected.value = Icons.Default.AccountCircle
            navController.navigate(Screens.Profile.screen) {
                popUpTo(Screens.Home.screen) { inclusive = false }
            }
        }, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.AccountCircle, contentDescription = "Profile", Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.AccountCircle) Color.White else Color.DarkGray)
        }
    }

    // Add bottom sheet
    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet.value = false },
            sheetState = bottomSheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Create New",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Create Post Button
                Button(
                    onClick = {
                        Toast.makeText(context, "Create Post selected", Toast.LENGTH_SHORT).show()
                        scope.launch {
                            bottomSheetState.hide()
                            showBottomSheet.value = false
                            // Navigate to create post screen
                            navController.navigate(Screens.CreatePost.screen)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Create Post")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Create Question Button
                Button(
                    onClick = {
                        Toast.makeText(context, "Create Question selected", Toast.LENGTH_SHORT).show()
                        scope.launch {
                            bottomSheetState.hide()
                            showBottomSheet.value = false
                            // Navigate to create question screen
                            navController.navigate(Screens.CreateQuestion.screen)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Create Question")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Create Group Button
                Button(
                    onClick = {
                        Toast.makeText(context, "Create Group selected", Toast.LENGTH_SHORT).show()
                        scope.launch {
                            bottomSheetState.hide()
                            showBottomSheet.value = false
                            // Navigate to create group screen
                            navController.navigate(Screens.CreateGroup.screen)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Create Group")
                }

                // Add space at the bottom for better UX
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}