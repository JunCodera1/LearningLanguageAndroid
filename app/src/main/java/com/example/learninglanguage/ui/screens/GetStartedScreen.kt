import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learninglanguage.R
import com.example.learninglanguage.ui.components.BtnGetStarted
import com.example.learninglanguage.ui.components.BtnHaveAccount
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Data class để lưu trữ thông tin của mỗi trang
data class PageData(
    val imageRes: Int, // ID hình ảnh
    val title: String // Tiêu đề
)

@Composable
fun GetStartedScreen(navController: NavController) {
    // Danh sách dữ liệu cho các trang
    val pages = listOf(
        PageData(R.drawable.teaching, "Welcome to Language Learning"),
        PageData(R.drawable.education, "Learn Anytime, Anywhere"),
        PageData(R.drawable.knowledge, "Track Your Progress"),
        PageData(R.drawable.develop_activity, "Get Started Now")
    )

    val pagerState = rememberPagerState(pageCount = { pages.size }) // Số trang bằng số phần tử trong danh sách


    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000L) // Dừng 3 giây trước khi chuyển trang
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(
                page = nextPage,
                animationSpec = tween(durationMillis = 1500) // Làm chậm hiệu ứng chuyển động
            )
        }
    }
//val gradient = Brush.verticalGradient(
//    colors = listOf(
//        Color(0xFF2A1B3D), // Màu tím đậm (gần giống giữa gradient)
//        Color(0xFF1A0D2C)
//
//    ),
//    startY = 0f,
//    endY = 600f
//
//)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // HorizontalPager để hiển thị các trang
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            val pageData = pages[page] // Lấy dữ liệu của trang hiện tại

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Hiển thị hình ảnh
                Image(
                    painter = painterResource(id = pageData.imageRes),
                    contentDescription = "Page Image",
                    modifier = Modifier.size(400.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Hiển thị tiêu đề
                Text(
                    text = pageData.title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        // Dấu chấm chỉ trang (Page Indicators) với hình chữ nhật dài khi được chọn
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val isSelected = pagerState.currentPage == iteration
                val width = if (isSelected) 32.dp else 16.dp // Chiều rộng thay đổi
                val color = if (isSelected) Color.DarkGray else Color.LightGray

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(RoundedCornerShape(4.dp)) // Bo góc
                        .background(color)
                        .width(width) // Chiều rộng thay đổi
                        .height(8.dp) // Chiều cao cố định
                        .animateContentSize() // Hiệu ứng chuyển đổi mượt mà
                )
            }
        }

        // Nút "Get Started"
        BtnGetStarted ({
            navController.navigate("signup") // Chuyển sang màn hình đăng ký
        })

        Spacer(modifier = Modifier.height(16.dp))

        // Nút "I Already have an account"
        BtnHaveAccount ({
            navController.navigate("login") // Chuyển sang màn hình đăng nhập
        })
    }
}
