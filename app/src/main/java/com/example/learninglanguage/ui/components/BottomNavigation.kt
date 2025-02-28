package com.example.learninglanguage.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learninglanguage.navigation.NavItem
@Composable
fun BottomNavigation(modifier: Modifier = Modifier){
    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Notification", Icons.Default.Notifications),
        NavItem("Settings", Icons.Default.Settings)
    )
    val selectedIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed{ _, navItem ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = navItem.icon, contentDescription = navItem.label) },
                        label = { Text(text = navItem.label) },
                        selected = true, // Cần logic xử lý trạng thái được chọn
                        onClick = { /* TODO: Handle navigation */ }
                    )
                }
            }
        }
    ) {  innerPadding ->
        Text(
            text = "Selected: ${navItemList[selectedIndex].label}",
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        )
    }
}