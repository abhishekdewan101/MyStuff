package com.adewan.mystuff.ui.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.model.TmdbMovie
import com.adewan.mystuff.core.repository.TmdbRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val tmdbRepository: TmdbRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<TmdbMovie?>(null)
    val viewState = _viewState.asStateFlow()

    fun getMovieDetails(identifier: String) {
        viewModelScope.launch {
            _viewState.value = tmdbRepository.getTmdbMovieDetails(identifier = identifier)
        }
    }
}
