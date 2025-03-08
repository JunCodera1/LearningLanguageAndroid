package com.example.learninglanguage.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguage.ui.components.LessonGrid
import com.example.learninglanguage.ui.components.TopAppBarSection
import com.example.learninglanguage.viewmodel.AuthState
import com.example.learninglanguage.viewmodel.AuthViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        if (authState.value is AuthState.Unauthenticated && navController.currentDestination?.route != "login") {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBarSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Lesson List
            LessonGrid(navController)

            Spacer(modifier = Modifier.weight(1f))
        }

        // Sign Out Button in the top-right corner
        IconButton(
            onClick = {
                authViewModel.signout()
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier.align(Alignment.TopEnd) // Align to top-right
        ) {
            Icon(
                imageVector = Icons.Filled.ExitToApp,
                contentDescription = "Sign Out"
            )
        }

    }
}
