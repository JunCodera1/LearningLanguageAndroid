package com.example.learninglanguage.ui.screens.feature

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.learninglanguage.ui.theme.GreenJC
import com.example.learninglanguage.viewmodel.SearchViewModel

@Composable
fun SearchScreen(navController: NavController, searchViewModel: SearchViewModel = viewModel()) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val searchResults by searchViewModel.searchResults.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Thanh tìm kiếm
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                searchViewModel.search(it.text) // Gửi truy vấn tìm kiếm
            },
            label = { Text("Tìm kiếm...") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Danh sách kết quả tìm kiếm
        if (searchResults.isNotEmpty()) {
            Column {
                searchResults.forEach { result ->
                    Text(
                        text = result,
                        fontSize = 18.sp,
                        color = GreenJC,
                        modifier = Modifier.padding(8.dp)
                    )
                    Divider(color = Color.Gray, thickness = 0.5.dp)
                }
            }
        } else {
            Text(text = "Not found the result", fontSize = 16.sp, color = Color.Gray)
        }
    }
}
