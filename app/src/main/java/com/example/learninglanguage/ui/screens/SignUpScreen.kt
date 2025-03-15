package com.example.learninglanguage.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguage.ui.components.BtnLoginWithFB
import com.example.learninglanguage.ui.components.BtnSignUp
import com.example.learninglanguage.ui.components.TextButtonHaveAccount
import com.example.learninglanguage.viewmodel.AuthState
import com.example.learninglanguage.viewmodel.AuthViewModel

@Composable
fun SignUpPage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val nameState = remember { mutableStateOf("") }
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        TextField(
            value = nameState.value,
            onValueChange = { newText ->
                if (newText.length <= 30) { // Giới hạn độ dài tối đa là 30 ký tự
                    nameState.value = newText
                }
            },
            label = { Text("Name") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .width(400.dp)
                .height(56.dp), // Cố định chiều cao
            singleLine = true, // Chỉ cho phép một dòng
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )

        Spacer(modifier = Modifier.height(60.dp))
        TextField(
            value = emailState.value,
            onValueChange = { newText ->
                if (newText.length <= 50) { // Giới hạn độ dài tối đa là 50 ký tự
                    emailState.value = newText
                }
            },
            label = { Text("Email") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .width(400.dp)
                .height(56.dp), // Cố định chiều cao
            singleLine = true, // Chỉ cho phép một dòng
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(60.dp))
        TextField(
            value = passwordState.value,
            onValueChange = { newText ->
                if (newText.length <= 20) { // Giới hạn độ dài tối đa là 20 ký tự
                    passwordState.value = newText
                }
            },
            label = { Text("Password") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .width(400.dp)
                .height(56.dp), // Cố định chiều cao
            singleLine = true, // Chỉ cho phép một dòng
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.height(60.dp))
        BtnSignUp {
            authViewModel.signup(nameState.value, emailState.value, passwordState.value)
        }

        Spacer(modifier = Modifier.height(30.dp))
        BtnLoginWithFB {
            // Thêm logic đăng nhập với Facebook nếu có
        }

        Spacer(modifier = Modifier.height(30.dp))
        TextButtonHaveAccount {
            navController.navigate("login")
        }
    }
}