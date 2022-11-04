package com.adewan.mystuff.ui.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.model.TmdbMovie
import com.adewan.mystuff.core.model.TmdbProvider
import com.adewan.mystuff.core.model.TmdbResultList
import com.adewan.mystuff.core.model.TmdbScreenshotList
import com.adewan.mystuff.core.model.TmdbVideoList
import com.adewan.mystuff.core.repository.TmdbRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MovieDetailState(
    val tmdbMovie: TmdbMovie,
    val screenshotList: TmdbScreenshotList,
    val videoList: TmdbVideoList,
    val providersList: List<TmdbProvider>,
    val similarMovies: TmdbResultList<TmdbMovie>,
)

class MovieDetailViewModel(private val tmdbRepository: TmdbRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<MovieDetailState?>(null)
    val viewState = _viewState.asStateFlow()

    fun getMovieDetails(identifier: String) {
        viewModelScope.launch {
            val data1 = async { tmdbRepository.getTmdbMovieDetails(identifier = identifier) }
            val data2 = async { tmdbRepository.getTmdbMovieScreenshots(identifier = identifier) }
            val data3 = async { tmdbRepository.getTmdbMovieVideos(identifier = identifier) }
            val data4 = async { tmdbRepository.getTmdbMovieProviders(identifier = identifier) }
            val data5 = async { tmdbRepository.getSimilarTmdbMovies(identifier = identifier) }
            _viewState.value = MovieDetailState(
                tmdbMovie = data1.await(),
                screenshotList = data2.await(),
                videoList = data3.await(),
                providersList = data4.await(),
                similarMovies = data5.await(),
            )
        }
    }
}
