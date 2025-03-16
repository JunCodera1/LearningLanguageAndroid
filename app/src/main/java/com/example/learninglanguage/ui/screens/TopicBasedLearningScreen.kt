package com.example.learninglanguage.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

data class VocabularyTopic(
    val id: String,
    val name: String,
    val description: String,
    val wordsCount: Int,
    val icon: ImageVector,
    val accentColor: Color,
    val words: List<WordItem>
)

data class WordItem(
    val original: String,
    val translation: String,
    val phonetic: String = "",
    val example: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicBasedLearningScreen() {
    val topics = remember {
        listOf(
            VocabularyTopic(
                id = "travel",
                name = "Du l\u1ecbch",
                description = "T\u1eeb v\u1ef1ng c\u1ea7n thi\u1ebft khi \u0111i du l\u1ecbch",
                wordsCount = 25,
                icon = Icons.Default.FlightTakeoff,
                accentColor = Color(0xFF2196F3),
                words = listOf(
                    WordItem(
                        original = "Airport",
                        translation = "S�n bay",
                        phonetic = "/\u02c8e\u0259.p\u0254\u02d0t/",
                        example = "We arrived at the airport two hours early."
                    ),
                    WordItem(
                        original = "Hotel",
                        translation = "Kh�ch s\u1ea1n",
                        phonetic = "/h\u0259\u028a\u02c8tel/",
                        example = "We stayed at a five-star hotel."
                    ),
                    WordItem(
                        original = "Passport",
                        translation = "H\u1ed9 chi\u1ebfu",
                        phonetic = "/\u02c8p\u0251\u02d0s.p\u0254\u02d0t/",
                        example = "Don't forget to bring your passport."
                    ),
                    WordItem(
                        original = "Suitcase",
                        translation = "Vali",
                        phonetic = "/\u02c8su\u02d0t.ke\u026as/",
                        example = "I packed my suitcase the night before."
                    ),
                    WordItem(
                        original = "Ticket",
                        translation = "V�",
                        phonetic = "/\u02c8t\u026ak.\u026at/",
                        example = "You can book tickets online."
                    )
                )
            ),
            VocabularyTopic(
                id = "food",
                name = "Th\u1ee9c \u0103n",
                description = "T\u1eeb v\u1ef1ng v\u1ec1 th\u1ee9c \u0103n v� \u0111\u1ed3 u\u1ed1ng",
                wordsCount = 30,
                icon = Icons.Default.Restaurant,
                accentColor = Color(0xFFFF9800),
                words = listOf(
                    WordItem(
                        original = "Rice",
                        translation = "C\u01a1m",
                        phonetic = "/ra\u026as/",
                        example = "Rice is a staple food in many Asian countries."
                    ),
                    WordItem(
                        original = "Noodles",
                        translation = "M�",
                        phonetic = "/\u02c8nu\u02d0.dl\u0329z/",
                        example = "I love eating noodles with vegetables."
                    ),
                    WordItem(
                        original = "Chicken",
                        translation = "Th\u1ecbt g�",
                        phonetic = "/\u02c8t\u0283\u026ak.\u026an/",
                        example = "We had grilled chicken for dinner."
                    ),
                    WordItem(
                        original = "Coffee",
                        translation = "C� ph�",
                        phonetic = "/\u02c8k\u0252f.i/",
                        example = "Would you like some coffee?"
                    ),
                    WordItem(
                        original = "Water",
                        translation = "N\u01b0\u1edbc",
                        phonetic = "/\u02c8w\u0254\u02d0.t\u0259r/",
                        example = "Can I have a glass of water, please?"
                    )
                )
            ),
            VocabularyTopic(
                id = "work",
                name = "C�ng vi\u1ec7c",
                description = "T\u1eeb v\u1ef1ng li�n quan \u0111\u1ebfn c�ng vi\u1ec7c v� v\u0103n ph�ng",
                wordsCount = 20,
                icon = Icons.Default.Work,
                accentColor = Color(0xFF4CAF50),
                words = listOf(
                    WordItem(
                        original = "Office",
                        translation = "V\u0103n ph�ng",
                        phonetic = "/\u02c8\u0252f.\u026as/",
                        example = "My office is on the second floor."
                    ),
                    WordItem(
                        original = "Meeting",
                        translation = "Cu\u1ed9c h\u1ecdp",
                        phonetic = "/\u02c8mi\u02d0.t\u026a\u014b/",
                        example = "We have a team meeting at 2 PM."
                    ),
                    WordItem(
                        original = "Colleague",
                        translation = "\u0110\u1ed3ng nghi\u1ec7p",
                        phonetic = "/\u02c8k\u0252l.i\u02d0\u0261/",
                        example = "My colleagues are very friendly."
                    ),
                    WordItem(
                        original = "Report",
                        translation = "B�o c�o",
                        phonetic = "/r\u026a\u02c8p\u0254\u02d0t/",
                        example = "I need to finish this report by tomorrow."
                    ),
                    WordItem(
                        original = "Email",
                        translation = "Th\u01b0 \u0111i\u1ec7n t\u1eed",
                        phonetic = "/\u02c8i\u02d0.me\u026al/",
                        example = "I sent you an email this morning."
                    )
                )
            ),
            VocabularyTopic(
                id = "family",
                name = "Gia \u0111�nh",
                description = "T\u1eeb v\u1ef1ng v\u1ec1 gia \u0111�nh v� ng\u01b0\u1eddi th�n",
                wordsCount = 15,
                icon = Icons.Default.People,
                accentColor = Color(0xFFE91E63),
                words = listOf(
                    WordItem(
                        original = "Mother",
                        translation = "M\u1eb9",
                        phonetic = "/\u02c8m\u028c�.\u0259r/",
                        example = "My mother is a doctor."
                    ),
                    WordItem(
                        original = "Father",
                        translation = "Cha",
                        phonetic = "/\u02c8f\u0251\u02d0.�\u0259r/",
                        example = "My father likes fishing."
                    ),
                    WordItem(
                        original = "Sister",
                        translation = "Ch\u1ecb/em g�i",
                        phonetic = "/\u02c8s\u026as.t\u0259r/",
                        example = "My sister is older than me."
                    ),
                    WordItem(
                        original = "Brother",
                        translation = "Anh/em trai",
                        phonetic = "/\u02c8br\u028c�.\u0259r/",
                        example = "My brother plays football."
                    ),
                    WordItem(
                        original = "Grandparents",
                        translation = "�ng b�",
                        phonetic = "/\u02c8\u0261r�nd.p\u025b\u0259.r\u0259nts/",
                        example = "We visit our grandparents every weekend."
                    )
                )
            ),
            VocabularyTopic(
                id = "health",
                name = "S\u1ee9c kh\u1ecfe",
                description = "T\u1eeb v\u1ef1ng v\u1ec1 s\u1ee9c kh\u1ecfe v� y t\u1ebf",
                wordsCount = 22,
                icon = Icons.Default.Favorite,
                accentColor = Color(0xFFFF5252),
                words = listOf(
                    WordItem(
                        original = "Doctor",
                        translation = "B�c s\u0129",
                        phonetic = "/\u02c8d\u0252k.t\u0259r/",
                        example = "I need to see a doctor."
                    ),
                    WordItem(
                        original = "Hospital",
                        translation = "B\u1ec7nh vi\u1ec7n",
                        phonetic = "/\u02c8h\u0252s.p\u026a.t\u0259l/",
                        example = "The hospital is near the city center."
                    ),
                    WordItem(
                        original = "Medicine",
                        translation = "Thu\u1ed1c",
                        phonetic = "/\u02c8med.\u026a.s\u0259n/",
                        example = "Take this medicine three times a day."
                    ),
                    WordItem(
                        original = "Fever",
                        translation = "S\u1ed1t",
                        phonetic = "/\u02c8fi\u02d0.v\u0259r/",
                        example = "She has a high fever."
                    ),
                    WordItem(
                        original = "Healthy",
                        translation = "Kh\u1ecfe m\u1ea1nh",
                        phonetic = "/\u02c8hel.\u03b8i/",
                        example = "Exercise is important for a healthy lifestyle."
                    )
                )
            ),
            VocabularyTopic(
                id = "technology",
                name = "C�ng ngh\u1ec7",
                description = "T\u1eeb v\u1ef1ng v\u1ec1 m�y t�nh v� c�ng ngh\u1ec7",
                wordsCount = 28,
                icon = Icons.Default.Computer,
                accentColor = Color(0xFF9C27B0),
                words = listOf(
                    WordItem(
                        original = "Computer",
                        translation = "M�y t�nh",
                        phonetic = "/k\u0259m\u02c8pju\u02d0.t\u0259r/",
                        example = "I need to buy a new computer."
                    ),
                    WordItem(
                        original = "Smartphone",
                        translation = "\u0110i\u1ec7n tho\u1ea1i th�ng minh",
                        phonetic = "/\u02c8sm\u0251\u02d0t.f\u0259\u028an/",
                        example = "Most people have smartphones these days."
                    ),
                    WordItem(
                        original = "Internet",
                        translation = "Internet",
                        phonetic = "/\u02c8\u026an.t\u0259.net/",
                        example = "We have fast internet at home."
                    ),
                    WordItem(
                        original = "Software",
                        translation = "Ph\u1ea7n m\u1ec1m",
                        phonetic = "/\u02c8s\u0252ft.we\u0259r/",
                        example = "This software is easy to use."
                    ),
                    WordItem(
                        original = "Website",
                        translation = "Trang web",
                        phonetic = "/\u02c8web.sa\u026at/",
                        example = "Visit our website for more information."
                    )
                )
            )
        )
    }

    var selectedTopic by remember { mutableStateOf<VocabularyTopic?>(null) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    if (selectedTopic == null) {
        // Topic selection screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ch\u1ecdn ch\u1ee7 \u0111\u1ec1",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "H�y ch\u1ecdn ch\u1ee7 \u0111\u1ec1 b\u1ea1n mu\u1ed1n h\u1ecdc",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(topics) { topic ->
                    TopicCard(
                        topic = topic,
                        onClick = { selectedTopic = topic }
                    )
                }
            }
        }
    } else {
        // Topic details screen with drawer
        DismissibleNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DismissibleDrawerSheet {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(300.dp)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Danh sách từ vựng",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        selectedTopic?.words?.forEach { word ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = word.original,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Text(
                                            text = word.translation
                                        )
                                    }

                                    if (word.phonetic.isNotEmpty()) {
                                        Text(
                                            text = word.phonetic,
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.padding(top = 4.dp)
                                        )
                                    }

                                    if (word.example.isNotEmpty()) {
                                        Text(
                                            text = "\"${word.example}\"",
                                            fontSize = 12.sp,
                                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.padding(top = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            },
            content = {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = selectedTopic!!.name) },
                            navigationIcon = {
                                IconButton(onClick = { selectedTopic = null }) {
                                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                                }
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        coroutineScope.launch {
                                            if (drawerState.isClosed) {
                                                drawerState.open()
                                            } else {
                                                drawerState.close()
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.List,
                                        contentDescription = "Danh sách từ vựng"
                                    )
                                }
                            }
                        )
                    }
                ) { _ ->
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = selectedTopic!!.name) },
                        navigationIcon = {
                            IconButton(onClick = { selectedTopic = null }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        if (drawerState.isClosed) {
                                            drawerState.open()
                                        } else {
                                            drawerState.close()
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    Icons.Default.List,
                                    contentDescription = "Danh sách từ vựng"
                                )
                            }
                        }
                    )
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        Icon(
                            imageVector = selectedTopic!!.icon,
                            contentDescription = null,
                            tint = selectedTopic!!.accentColor,
                            modifier = Modifier.size(48.dp)
                        )

                        Column {
                            Text(
                                text = selectedTopic!!.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = selectedTopic!!.description,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Text(
                                text = "${selectedTopic!!.wordsCount} từ vựng",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Học từ vựng",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Button(
                                onClick = { /* TODO: Implement flashcard feature */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = selectedTopic!!.accentColor
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    Icons.Default.School,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text("Flashcards")
                            }
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Kiểm tra",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Button(
                                onClick = { /* TODO: Implement quiz feature */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = selectedTopic!!.accentColor
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    Icons.Default.QuestionAnswer,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text("Trắc nghiệm")
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = { /* TODO: Implement matching game */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = selectedTopic!!.accentColor
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    Icons.Default.Extension,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text("Trò chơi ghép từ")
                            }
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Thống kê",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            LinearProgressIndicator(
                                progress = 0.35f,
                                modifier = Modifier.run {
                                    fillMaxWidth()
                                                                .height(8.dp)
                                                                .clip(RoundedCornerShape(4.dp))
                                },
                                color = selectedTopic!!.accentColor
                            )

                            Text(
                                text = "35% hoàn thành",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    })
}

@Composable
fun TopicCard(
    topic: VocabularyTopic,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(topic.accentColor.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = topic.icon,
                    contentDescription = null,
                    tint = topic.accentColor,
                    modifier = Modifier.size(36.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = topic.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "${topic.wordsCount} từ vựng",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)
                )
                }
            }
        }
    }
}

@Composable
fun TopicCard(topic: VocabularyTopic, onClick: () -> Unit) {

}
