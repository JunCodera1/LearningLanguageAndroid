package com.example.learninglanguage

import TopAppBarSection
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.learninglanguage.navigation.Navigation
import com.example.learninglanguage.ui.components.BottomNavigation
import com.example.learninglanguage.ui.theme.LearningLanguageTheme
import com.example.learninglanguage.viewmodel.AuthState
import com.example.learninglanguage.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel: AuthViewModel by viewModels()

        setContent {
            LearningLanguageTheme {
                MainScreen(authViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.observeAsState(AuthState.Unauthenticated)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBarSection(navController, authViewModel) },
        bottomBar = {
            if (authState is AuthState.Authenticated) {
                BottomNavigation(navController, authViewModel)
            }
        }
    ) { paddingValues ->
        Navigation(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            authViewModel = authViewModel,
            navController = navController
        )
    }
}
