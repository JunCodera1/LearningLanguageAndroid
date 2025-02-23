package com.example.learninglanguage.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BtnHaveAccount(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.width(400.dp)
    ) {
        Text("I Already have an account")
    }
}

@Composable
fun TextButtonHaveAccount(onClick: () -> Unit){
    TextButton(
        onClick = onClick
    ) {
        Text("Already have an account ?")
    }
}