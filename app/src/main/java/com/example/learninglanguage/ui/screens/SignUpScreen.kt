package com.example.learninglanguage.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguage.R
import com.example.learninglanguage.ui.components.BtnSignUp
import com.example.learninglanguage.ui.components.TextButtonHaveAccount
import com.example.learninglanguage.viewmodel.AuthState
import com.example.learninglanguage.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val authState by authViewModel.authState.observeAsState()
    val context = LocalContext.current

    // Xử lý trạng thái đăng ký
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                navController.navigate("home") {
                    popUpTo("signup") { inclusive = true }
                }
            }
            is AuthState.Error -> {
                Toast.makeText(context, (authState as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Nhập tên
        OutlinedTextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            label = { Text("Name") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(350.dp),
            singleLine = true
        )

        // Nhập email
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(350.dp),
            singleLine = true
        )

        // Nhập mật khẩu
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(350.dp),
            singleLine = true
        )

        // Nút Đăng ký
        BtnSignUp(
            onClick = {
                authViewModel.signup(nameState.value, emailState.value, passwordState.value)
            },
            isLoading = authState is AuthState.Loading
        )


        Button(
            onClick = { /* Xử lý Google Sign Up */ },
            modifier = Modifier.width(350.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Android, // Đổi thành Google nếu có
                contentDescription = "Google Icon"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign up with Google")
        }


        // Sign Up with Facebook
        Button(
            onClick = {
                // TODO: Implement Facebook sign-up logic
            },
            modifier = Modifier.width(350.dp)
        ) {
            Icon(imageVector = Icons.Filled.Facebook, contentDescription = "Facebook Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign up with Facebook")
        }
        TextButtonHaveAccount {
            navController.navigate("login")
        }
    }
}
