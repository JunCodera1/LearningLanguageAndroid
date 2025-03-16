package com.example.learninglanguage.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel : ViewModel() {
    private val _searchResults = MutableStateFlow<List<String>>(emptyList())
    val searchResults = _searchResults.asStateFlow()

    private val dataList = listOf("Kotlin", "Java", "Python", "Jetpack Compose", "Android", "Swift", "C++")

    fun search(query: String) {
        _searchResults.value = if (query.isBlank()) {
            emptyList()
        } else {
            dataList.filter { it.contains(query, ignoreCase = true) }
        }
    }
}
