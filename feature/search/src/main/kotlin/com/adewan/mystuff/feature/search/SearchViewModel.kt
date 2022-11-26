package com.adewan.mystuff.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.data.repositories.SearchRepository
import com.adewan.mystuff.core.data.repositories.SearchResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<SearchViewState>(SearchViewState.Start)
    val viewState = _viewState.asStateFlow()

    fun startSearch(searchTerm: String) {
        _viewState.value = SearchViewState.Loading
        viewModelScope.launch {
            delay(1000)
            _viewState.value =
                SearchViewState.Results(results = searchRepository.searchSources(searchTerm))
        }
    }

    fun clearSearchResults() {
        _viewState.value = SearchViewState.Start
    }
}

sealed interface SearchViewState {
    object Start : SearchViewState
    object Loading : SearchViewState
    data class Results(val results: List<SearchResult>) : SearchViewState
}
