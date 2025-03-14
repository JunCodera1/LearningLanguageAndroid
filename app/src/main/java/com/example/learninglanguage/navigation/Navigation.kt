package com.example.learninglanguage.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.learninglanguage.ui.components.BottomNavigation
import com.example.learninglanguage.ui.screens.*
import com.example.learninglanguage.viewmodel.AuthViewModel
import com.example.learninglanguage.viewmodel.SearchViewModel

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
        composable("home") { HomeScreen(navController = navController, authViewModel = authViewModel) }
        composable("profile") { ProfileScreen(navController = navController, authViewModel = authViewModel) }
        composable("settings") { SettingsScreen(navController = navController) }
        composable("search"){ SearchScreen(navController = navController) }
        composable("notification"){ NotificationScreen(navController = navController, authViewModel= authViewModel) }
        composable("login") { LoginScreen(navController = navController, authViewModel = authViewModel) }
        composable("signup") { SignUpScreen(navController = navController, authViewModel = authViewModel) }
    }
}

