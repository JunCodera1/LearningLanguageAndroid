package com.example.learninglanguage.navigation


import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.learninglanguage.ui.screens.auth.LoginScreen
import com.example.learninglanguage.ui.screens.auth.SignUpScreen
import com.example.learninglanguage.ui.screens.create.CreateGroupScreen
import com.example.learninglanguage.ui.screens.create.CreatePostScreen
import com.example.learninglanguage.ui.screens.create.CreateQuestionScreen
import com.example.learninglanguage.ui.screens.feature.NotificationScreen
import com.example.learninglanguage.ui.screens.feature.SearchScreen
import com.example.learninglanguage.ui.screens.feature.SettingsScreen
import com.example.learninglanguage.ui.screens.flashcard.FlashCardScreen
import com.example.learninglanguage.ui.screens.home.HomeScreen
import com.example.learninglanguage.ui.screens.learning.LearningGamesScreen
import com.example.learninglanguage.ui.screens.learning.PronunciationScreen
import com.example.learninglanguage.ui.screens.profile.ProfileScreen
import com.example.learninglanguage.ui.screens.topic.TopicBasedLearningScreen
import com.example.learninglanguage.viewmodel.AuthViewModel

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "home", // Mặc định vào màn hình Home
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable("profile") {
            ProfileScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable("settings") { SettingsScreen(navController = navController) }
        composable("search"){ SearchScreen(navController = navController) }
        composable("notification"){
            NotificationScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable("login") {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable("signup") {
            SignUpScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable("flash-card"){ FlashCardScreen() }
        composable("pronunciation"){ PronunciationScreen() }
        composable("games"){ LearningGamesScreen() }
        composable("topic"){ TopicBasedLearningScreen() }
        composable("create-post"){ CreatePostScreen(navController) }
        composable("create-question"){ CreateQuestionScreen(navController) }
        composable("create-group"){ CreateGroupScreen(navController) }

    }
}

