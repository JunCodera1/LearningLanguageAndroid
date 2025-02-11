import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BtnGetStarted(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
        ,
        modifier = Modifier.width(400.dp)
    ) {
        Text("Get Started")
    }
}
@Composable
fun BtnHaveAccount(onClick: () -> Unit){
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.width(400.dp)
    ) {
        Text("I Already have an account")
    }
}


@Composable
fun MyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp)) // Tạo khoảng cách giữa hai nút

        Text("The free, fun, and effective way to learn a language")

        Spacer(modifier = Modifier.height(60.dp)) // Tạo khoảng cách giữa hai nút

        BtnGetStarted(onClick = {
            println("Get Started button clicked!")
        })

        Spacer(modifier = Modifier.height(16.dp)) // Tạo khoảng cách giữa hai nút

        BtnHaveAccount(onClick = {
            println("Have Account button clicked!")
        })
    }
}
