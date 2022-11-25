package com.adewan.mystuff.feature.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel : ViewModel() {
    private val _viewState = MutableStateFlow<SearchViewState>(SearchViewState.Start)
    val viewState = _viewState.asStateFlow()

    fun startSearch(searchTerm: String) {
        println("Abhishek --> $searchTerm")
        _viewState.value = SearchViewState.Loading
    }
}

sealed interface SearchViewState {
    object Start : SearchViewState
    object Loading : SearchViewState
}
