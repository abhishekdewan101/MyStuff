package com.adewan.mystuff.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _viewState = MutableStateFlow<SearchViewState>(SearchViewState.Start)
    val viewState = _viewState.asStateFlow()

    fun startSearch(searchTerm: String) {
        println("Abhishek --> $searchTerm")
        _viewState.value = SearchViewState.Loading
        viewModelScope.launch {
            delay(1000)
            _viewState.value = SearchViewState.Results
        }
    }
}

sealed interface SearchViewState {
    object Start : SearchViewState
    object Loading : SearchViewState
    object Results : SearchViewState
}
