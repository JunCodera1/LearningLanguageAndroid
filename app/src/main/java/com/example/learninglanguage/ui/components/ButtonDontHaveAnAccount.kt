package com.example.learninglanguage.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun BtnDontHaveAnAccount(onClick: () -> Unit){
    TextButton(onClick = onClick) {
        Text("Don't have an account? Sign Up")
    }
}