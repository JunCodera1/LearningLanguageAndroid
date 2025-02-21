import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learninglanguage.pages.LoginPage
import com.example.learninglanguage.pages.SignUpPage



@Composable
fun MyGetStartedScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text("The free, fun, and effective way to learn a language")

        Spacer(modifier = Modifier.height(60.dp))

        BtnGetStarted {
            navController.navigate("signup") // Chuyển sang màn hình đăng ký
        }

        Spacer(modifier = Modifier.height(16.dp))

        BtnHaveAccount {
            println("Have Account button clicked!")
            navController.navigate("signin")
        }
    }
}

@Composable
fun BtnGetStarted(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier.width(400.dp)
    ) {
        Text("Get Started")
    }
}

@Composable
fun BtnHaveAccount(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.width(400.dp)
    ) {
        Text("I Already have an account")
    }
}
