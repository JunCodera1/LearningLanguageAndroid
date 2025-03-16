package com.example.learninglanguage.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun BtnDontHaveAnAccount(onClick: () -> Unit){
    TextButton(onClick = onClick) {
        Text("Don't have an account? Sign Up")
    }
}