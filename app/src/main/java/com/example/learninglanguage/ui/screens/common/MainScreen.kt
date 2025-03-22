package com.example.learninglanguage.ui.screens.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.learninglanguage.navigation.Navigation
import com.example.learninglanguage.ui.components.nav.BottomNavigation
import com.example.learninglanguage.ui.components.nav.DrawerContent
import com.example.learninglanguage.ui.components.nav.TopBar
import com.example.learninglanguage.viewmodel.AuthState
import com.example.learninglanguage.viewmodel.AuthViewModel
import kotlinx.coroutines.launch
import kotlin.collections.contains

@Composable
fun MainScreen(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    // Use remember to create a temporary AuthState
    val authStateValue = remember { AuthState.Unauthenticated }

    val authState by authViewModel.authState.observeAsState()

    val drawState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Get current route
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination?.route

    // Define screens that don't need TopBar and BottomBar
    val hideNavScreens = listOf("login", "signup")
    val shouldShowNav = currentDestination !in hideNavScreens

    ModalNavigationDrawer(
        drawerState = drawState,
        drawerContent = {
            // Only show drawer when logged in and on visible nav screens
            if (shouldShowNav) {
                ModalDrawerSheet {
                    DrawerContent(navController = navController)
                }
            }
        },
        // Only enable drawer gestures when on visible nav screens
        gesturesEnabled = shouldShowNav
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                // Only show TopBar when not on login/signup screens
                if (shouldShowNav) {
                    TopBar(
                        onOpenDrawer = {
                            scope.launch {
                                if (drawState.isClosed) drawState.open() else drawState.close()
                            }
                        },
                        navController = navController,
                        authViewModel = authViewModel
                    )
                }
            },
            bottomBar = {
                // Only show BottomBar when not on login/signup screens
                if (shouldShowNav) {
                    BottomNavigation(navController = navController, authViewModel = authViewModel)
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