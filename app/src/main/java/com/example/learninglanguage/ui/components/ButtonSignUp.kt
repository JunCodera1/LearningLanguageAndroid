package com.example.learninglanguage.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BtnSignUp(onClick: () -> Unit, isLoading: Boolean) {
    OutlinedButton(
        onClick = { if (!isLoading) onClick() }, // Chặn khi đang loading
        modifier = Modifier.width(400.dp),
        enabled = !isLoading // Disable khi đang loading
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(if (isLoading) "Loading..." else "Sign Up")
        }
    }
}
