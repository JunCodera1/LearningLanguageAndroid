package com.example.learninglanguage.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopAppBarSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* TODO: Mở menu */ }) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
        }
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Text(text = "85", fontSize = 18.sp, color = Color.Yellow)
//            Spacer(modifier = Modifier.width(8.dp))
//            Icon(imageVector = Icons.Filled.Whatshot, contentDescription = "Streak", tint = Color.Red)
//            Text(text = "12", fontSize = 18.sp, color = Color.Red)
//            Spacer(modifier = Modifier.width(8.dp))
//            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Hearts", tint = Color.Magenta)
//            Text(text = "5", fontSize = 18.sp, color = Color.Magenta)
//        }
    }
}
