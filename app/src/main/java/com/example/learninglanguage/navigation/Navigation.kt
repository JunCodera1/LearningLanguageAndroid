package com.example.learninglanguage.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learninglanguage.ui.screens.HomeScreen
import com.example.learninglanguage.ui.screens.LoginPage
import com.example.learninglanguage.ui.screens.SignUpPage
import com.example.learninglanguage.viewmodel.AuthViewModel

@Composable
fun Navigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){
            LoginPage(modifier, navController, authViewModel)
        }
        composable("signup"){
            SignUpPage(modifier, navController, authViewModel)
        }
        composable("home"){
            HomeScreen(modifier, navController, authViewModel)
        }
    })
}