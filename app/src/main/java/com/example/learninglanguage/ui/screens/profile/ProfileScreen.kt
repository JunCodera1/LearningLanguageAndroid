package com.example.learninglanguage.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.learninglanguage.R
import com.example.learninglanguage.ui.theme.GreenJC
import com.example.learninglanguage.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel) {
    val user = authViewModel.currentUser // Get user information

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Light background color
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // User avatar (supports URL images)
                val avatarUrl = user?.photoUrl ?: "" // Replace with actual avatar path
                if (avatarUrl.isNotEmpty()) {
                    AsyncImage(
                        model = avatarUrl,
                        contentDescription = "User Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.fb_logo),
                        contentDescription = "User Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Profile Title
                Text(
                    text = "Profile",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = GreenJC
                )

                Spacer(modifier = Modifier.height(8.dp))

                user?.let {
                    // User information
                    Text(text = it.name, fontSize = 22.sp, fontWeight = FontWeight.Medium)
                    Text(text = it.email, fontSize = 18.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.height(20.dp))

                    // Edit Profile Button
                    OutlinedButton(
                        onClick = { /* TODO: Navigate to edit profile screen */ },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = GreenJC)
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit Profile")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Edit Profile", fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                } ?: Text(
                    text = "User not logged in",
                    fontSize = 18.sp,
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Logout Button
                Button(
                    onClick = {
                        authViewModel.signout()
                        navController.navigate( "login") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenJC),
                    modifier = Modifier.fillMaxWidth(0.6f) // Limit width
                ) {
                    Text("Logout", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    }
}