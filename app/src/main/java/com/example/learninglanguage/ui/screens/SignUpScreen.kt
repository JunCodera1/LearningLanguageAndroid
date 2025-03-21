package com.example.learninglanguage.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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

    // Handle registration state
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
        // Name input
        OutlinedTextField(
            value = nameState.value,
            onValueChange = { newText ->
                if (newText.length <= 30) { // Max length of 30 characters
                    nameState.value = newText
                }
            },
            label = { Text("Name") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(350.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )

        // Email input
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { newText ->
                if (newText.length <= 50) { // Max length of 50 characters
                    emailState.value = newText
                }
            },
            label = { Text("Email") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(350.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        // Password input
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { newText ->
                if (newText.length <= 20) { // Max length of 20 characters
                    passwordState.value = newText
                }
            },
            label = { Text("Password") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(350.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        // Sign Up button
        BtnSignUp(
            onClick = {
                authViewModel.signup(nameState.value, emailState.value, passwordState.value)
            },
            isLoading = authState is AuthState.Loading
        )

        // Google Sign Up button
        Button(
            onClick = { /* Handle Google Sign Up */ },
            modifier = Modifier.width(350.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Android, // Change to Google if available
                contentDescription = "Google Icon"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign up with Google")
        }

        // Facebook Sign Up button
        Button(
            onClick = {
                // Facebook sign-up logic
            },
            modifier = Modifier.width(350.dp)
        ) {
            Icon(imageVector = Icons.Filled.Facebook, contentDescription = "Facebook Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign up with Facebook")
        }

        // Already have account text button
        TextButtonHaveAccount {
            navController.navigate("login")
        }
    }
}