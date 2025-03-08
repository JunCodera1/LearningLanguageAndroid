package com.example.learninglanguage.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguage.viewmodel.AuthViewModel

@Composable
fun ButtonSignOut(navController: NavController, authViewModel: AuthViewModel) {
    Button(
        onClick = {
            authViewModel.signout()
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "Sign Out")
        Spacer(modifier = Modifier.width(8.dp))
        Text("Sign Out")
    }
}