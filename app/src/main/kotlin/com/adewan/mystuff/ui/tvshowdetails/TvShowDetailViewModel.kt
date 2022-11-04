package com.adewan.mystuff.ui.tvshowdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.model.TmdbProvider
import com.adewan.mystuff.core.model.TmdbResultList
import com.adewan.mystuff.core.model.TmdbScreenshotList
import com.adewan.mystuff.core.model.TmdbTvShow
import com.adewan.mystuff.core.model.TmdbVideoList
import com.adewan.mystuff.core.repository.TmdbRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TvDetailState(
    val tmdbTvShow: TmdbTvShow,
    val screenshotList: TmdbScreenshotList,
    val videoList: TmdbVideoList,
    val providersList: List<TmdbProvider>,
    val similarMovies: TmdbResultList<TmdbTvShow>,
)

class TvShowDetailViewModel(private val tmdbRepository: TmdbRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<TvDetailState?>(null)
    val viewState = _viewState.asStateFlow()

    fun getMovieDetails(identifier: String) {
        viewModelScope.launch {
            val data1 = async { tmdbRepository.getTmdbTvShowDetails(identifier = identifier) }
            val data2 = async { tmdbRepository.getTmdbTvShowScreenshots(identifier = identifier) }
            val data3 = async { tmdbRepository.getTmdbTvShowVideos(identifier = identifier) }
            val data4 = async { tmdbRepository.getTmdbTvShowProviders(identifier = identifier) }
            val data5 = async { tmdbRepository.getSimilarTmdbTvShows(identifier = identifier) }
            _viewState.value = TvDetailState(
                tmdbTvShow = data1.await(),
                screenshotList = data2.await(),
                videoList = data3.await(),
                providersList = data4.await(),
                similarMovies = data5.await(),
            )
        }
    }
}
