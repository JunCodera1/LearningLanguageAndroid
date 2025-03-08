import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learninglanguage.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarSection(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var isSheetOpen by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Language Learning", fontSize = 20.sp) },
        navigationIcon = {
            IconButton(onClick = { isSheetOpen = true }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    )

    // Hiển thị Bottom Sheet khi bấm nút Menu
    if (isSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOpen = false },
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp) // Sử dụng `shape` thay vì `sheetShape`
        ) {
            MenuContent(
                navController = navController,
                authViewModel = authViewModel,
                onClose = { isSheetOpen = false }
            )
        }

    }
}

@Composable
fun MenuContent(
    navController: NavController,
    authViewModel: AuthViewModel,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar và thông tin người dùng
        Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Avatar", modifier = Modifier.size(60.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "John Doe", fontSize = 18.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        Text(text = "johndoe@example.com", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)

        Spacer(modifier = Modifier.height(16.dp))
        Divider()

        // Danh sách các mục menu
        MenuItem(icon = Icons.Filled.Person, title = "Profile") {
            onClose()
            navController.navigate("profile")
        }
        MenuItem(icon = Icons.Filled.Settings, title = "Settings") {
            onClose()
            navController.navigate("settings")
        }
        MenuItem(icon = Icons.Filled.Help, title = "Help & Support") {
            onClose()
            navController.navigate("help")
        }
        MenuItem(icon = Icons.Filled.Info, title = "About Us") {
            onClose()
            navController.navigate("about")
        }

        Divider()

        // Logout
        MenuItem(icon = Icons.Filled.Logout, title = "Logout") {
            onClose()
            authViewModel.signout()
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }
    }
}

@Composable
fun MenuItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = title, fontSize = 16.sp)
    }
}
