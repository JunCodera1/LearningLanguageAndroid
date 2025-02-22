package com.example.learninglanguage.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.learninglanguage.viewmodel.AuthState
import com.example.learninglanguage.viewmodel.AuthViewModel
import com.example.learninglanguage.R


@Composable
fun BtnLoginWithFB(onClick: () -> Unit){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier.width(400.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.fb_logo), // Thay thế bằng icon của bạn
                contentDescription = "Facebook Logo",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Login with FB")
        }
    }
}

@Composable
fun BtnSignUp(onClick: () -> Unit){
    OutlinedButton(
        onClick = onClick,

        modifier = Modifier.width(400.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign Up")
        }
    }
}

@Composable
fun BtnHaveAnAccount(onClick: () -> Unit){
    TextButton(
        onClick = onClick
    ) {
        Text("Already have an account ?")
    }
}

@Composable
fun SignUpPage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val nameState = remember { mutableStateOf("") }
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message,Toast.LENGTH_SHORT).show()
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
            onValueChange = { nameState.value = it },
            label = { Text("Name") },
            shape = RoundedCornerShape(16.dp), // Bo góc
            modifier = Modifier.width(400.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))

        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            shape = RoundedCornerShape(16.dp), // Bo góc
            modifier = Modifier.width(400.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))

        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            shape = RoundedCornerShape(16.dp), // Bo góc
            modifier = Modifier.width(400.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))

        BtnSignUp( {
            authViewModel.signup(nameState.value, emailState.value, passwordState.value)
        })
        Spacer(modifier = Modifier.height(30.dp))

        BtnLoginWithFB({
            // Thêm logic đăng nhập với Facebook nếu có
        })
        Spacer(modifier = Modifier.height(30.dp))

        BtnHaveAnAccount {
            navController.navigate("login")
        }
    }
}

