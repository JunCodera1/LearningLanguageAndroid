package com.example.learninglanguage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                val navController = rememberNavController()
                val authState by authViewModel.authState.observeAsState(AuthState.Unauthenticated) // Lắng nghe trạng thái đăng nhập

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (authState is AuthState.Authenticated) {
                            BottomNavigation(
                                navController = navController,
                                authViewModel = authViewModel
                            )
                        }
                    }
                ) { paddingValues ->
                    Navigation(
                        modifier = Modifier.padding(paddingValues),
                        authViewModel = authViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun MyBottomBarPreview(){
//    val authViewModel = AuthViewModel() // ⚠️ Đây chỉ để preview, không nên dùng trực tiếp
//
//    LearningLanguageTheme {
//        val navController = rememberNavController() // ⚡ Tạo NavController cho Preview
//        BottomNavigation(modifier = Modifier, navController = navController, authViewModel = authViewModel)
//    }
//}
