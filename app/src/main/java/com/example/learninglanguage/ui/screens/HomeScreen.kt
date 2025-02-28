package com.example.learninglanguage.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learninglanguage.viewmodel.AuthState
import com.example.learninglanguage.viewmodel.AuthViewModel
import com.example.learninglanguage.R // Import hình ảnh từ resources
import com.example.learninglanguage.ui.components.BottomNavigation
import com.example.learninglanguage.ui.components.LessonGrid
import com.example.learninglanguage.ui.components.TopAppBarSection

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBarSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Danh sách các bài học
        LessonGrid(navController)

        Spacer(modifier = Modifier.weight(1f))
        BottomNavigation()
//        // Nút Sign out
//        Button(onClick = { authViewModel.signout() }) {
//            Text(text = "Sign out")
//        }
    }
}

