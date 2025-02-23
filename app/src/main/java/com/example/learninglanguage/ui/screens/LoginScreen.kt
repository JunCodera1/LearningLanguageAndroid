package com.example.learninglanguage.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguage.ui.components.BtnDontHaveAnAccount
import com.example.learninglanguage.ui.components.BtnLogin
import com.example.learninglanguage.ui.components.BtnLoginWithFB
import com.example.learninglanguage.viewmodel.AuthViewModel





@Composable
fun LoginPage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel){
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            shape = RoundedCornerShape(16.dp), // Bo góc
            modifier = Modifier.width(400.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            shape = RoundedCornerShape(16.dp), // Bo góc
            modifier = Modifier.width(400.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))
        BtnLogin ({
         authViewModel.run { login(emailState.value, passwordState.value) }
        navController.navigate("home")
        })

        Spacer(modifier = Modifier.height(60.dp))

        BtnLoginWithFB({
            // Thêm logic đăng nhập với Facebook nếu có
        })
        Spacer(modifier = Modifier.height(30.dp))

        BtnDontHaveAnAccount ({
            navController.navigate("signup")
        })

    }
}