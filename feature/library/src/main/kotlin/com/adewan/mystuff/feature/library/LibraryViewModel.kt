package com.adewan.mystuff.feature.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.data.repositories.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LibraryViewModel(private val repository: GameRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<LibraryViewState>(LibraryViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllDBGames().collect {
                if (it.isEmpty()) {
                    _viewState.value = LibraryViewState.Empty
                } else {
                    _viewState.value = LibraryViewState.Results
                }
            }
        }
    }
}

sealed interface LibraryViewState {
    object Loading : LibraryViewState
    object Empty : LibraryViewState
    object Results : LibraryViewState
}
