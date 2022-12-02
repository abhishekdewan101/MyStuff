package com.adewan.mystuff.feature.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LibraryViewModel : ViewModel() {
    private val _viewState = MutableStateFlow<LibraryViewState>(LibraryViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            _viewState.value = LibraryViewState.Results
        }
    }
}

sealed interface LibraryViewState {
    object Loading : LibraryViewState
    object Empty : LibraryViewState
    object Results : LibraryViewState
}
