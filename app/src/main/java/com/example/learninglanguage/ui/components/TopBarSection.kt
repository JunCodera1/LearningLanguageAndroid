import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.CardMembership
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learninglanguage.R
import com.example.learninglanguage.viewmodel.AuthViewModel
import kotlinx.coroutines.launch


@Composable
fun TopAppBarSection(
    navController: NavController,
    authViewModel: AuthViewModel // Thêm lại authViewModel
) {
    val drawState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(navController = navController)
            }
        }
    ) {
        TopBar(
            onOpenDrawer = {
                scope.launch {
                    if (drawState.isClosed) drawState.open() else drawState.close()
                }
            },
            navController = navController,
            authViewModel = authViewModel // Truyền vào TopBar nếu cần
        )
    }
}



@Composable
fun DrawerContent(modifier: Modifier = Modifier, navController: NavController){

    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = null
    )

    Text(
        text = "App Name",
        fontSize = 24.sp,
        modifier = Modifier.padding(16.dp)
    )

    HorizontalDivider()

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Account",
                modifier = Modifier.size(27.dp)
            )
        },
        label ={
            Text(
                text = "Account",
                fontSize = 17.sp,
                modifier = Modifier.padding(16.dp)
            )
        } ,
        selected = false,
        onClick = {
            navController.navigate("profile")
        }
    )

    Spacer(modifier= Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notifications" ,
                modifier = Modifier.size(27.dp)

            )
        },
        label ={
            Text(
                text = "Notifications",
                fontSize = 17.sp,
                modifier = Modifier.padding(16.dp)
            )
        } ,
        selected = false,
        onClick = {
            navController.navigate("notification")
        }
    )

    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = "Settings",
                modifier = Modifier.size(27.dp)
            )
        },
        label ={
            Text(
                text = "Settings",
                fontSize = 17.sp,
                modifier = Modifier.padding(16.dp)
            )
        } ,
        selected = false,
        onClick = {navController.navigate("settings")}
    )
    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.CardMembership,
                contentDescription = "Flash Card",
                modifier = Modifier.size(27.dp)
            )
        },
        label ={
            Text(
                text = "Flash Card",
                fontSize = 17.sp,
                modifier = Modifier.padding(16.dp)
            )
        } ,
        selected = false,
        onClick = {}
    )
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onOpenDrawer: () -> Unit,
    navController: NavController,
    authViewModel: AuthViewModel // Nhận authViewModel
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.6f)
        ),
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(28.dp)
                    .clickable { onOpenDrawer() }
            )
        },
        title = {
            Text(text = "Screen Name")
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .size(30.dp)
            )

            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account",
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .size(30.dp)
                    .clickable {
                        navController.navigate("profile") // Chuyển đến trang Profile
                    }
            )
        }
    )
}