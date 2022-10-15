package com.adewan.mystuff.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.model.TmdbListType
import com.adewan.mystuff.core.usecase.GetComingSoonGames
import com.adewan.mystuff.core.usecase.GetRecentReleasedGames
import com.adewan.mystuff.core.usecase.GetShowcaseGames
import com.adewan.mystuff.core.usecase.GetTmdbMovieList
import com.adewan.mystuff.core.usecase.GetTmdbShowList
import com.adewan.mystuff.core.usecase.GetTopRatedGames
import com.adewan.mystuff.ui.composables.ImageCarouselWithTitleData
import com.adewan.mystuff.ui.composables.ImageShowcaseItem
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeViewState(
    val showcase: List<ImageShowcaseItem>,
    val topRated: ImageCarouselWithTitleData,
    val comingSoon: ImageCarouselWithTitleData,
    val recentReleased: ImageCarouselWithTitleData
)

enum class DataFilter {
    Games,
    Movies,
    Tv
}

class HomeViewModel(
    private val getShowcaseGames: GetShowcaseGames,
    private val getTopRatedGames: GetTopRatedGames,
    private val getComingSoonGames: GetComingSoonGames,
    private val getRecentReleasedGames: GetRecentReleasedGames,
    private val getTmdbMovieList: GetTmdbMovieList,
    private val getTmdbShowList: GetTmdbShowList
) : ViewModel() {
    private val _viewState = MutableStateFlow<HomeViewState?>(null)
    val viewState = _viewState.asStateFlow()

    private val _currentFilter = MutableStateFlow(DataFilter.Games)

    init {
        viewModelScope.launch {
            _currentFilter.collect {
                when (it) {
                    DataFilter.Games -> getGamesData()
                    DataFilter.Movies -> getMoviesData()
                    DataFilter.Tv -> getTvData()
                }
            }
        }
    }

    fun changeFilter(filter: DataFilter) {
        _currentFilter.value = filter
    }

    private fun getTvData() {
        _viewState.value = null
        viewModelScope.launch {
            val data1 = async {
                getTmdbShowList(TmdbListType.ON_AIR_TV_SHOWS)
                    .results
                    .filter { it.poster != null }
                    .map {
                        ImageShowcaseItem(url = it.posterUrl, label = it.name ?: it.originalName)
                    }
            }

            val data2 = async {
                ImageCarouselWithTitleData(
                    title = "Top Rated",
                    images = getTmdbShowList(TmdbListType.TOP_RATED_TV_SHOWS)
                        .results.filter { it.poster != null }
                        .map { it.posterUrl }
                )
            }

            val data3 = async {
                ImageCarouselWithTitleData(
                    title = "Popular",
                    images = getTmdbShowList(TmdbListType.POPULAR_TV_SHOWS)
                        .results.filter { it.poster != null }
                        .map { it.posterUrl }
                )
            }

            val data4 = async {
                ImageCarouselWithTitleData(
                    title = "Airing Today",
                    images = getTmdbShowList(TmdbListType.AIRING_TODAY_TV_SHOWS)
                        .results.filter { it.poster != null }
                        .map { it.posterUrl }
                )
            }

            _viewState.value = HomeViewState(
                showcase = data1.await(),
                topRated = data2.await(),
                comingSoon = data3.await(),
                recentReleased = data4.await()
            )
        }
    }

    private fun getMoviesData() {
        _viewState.value = null
        viewModelScope.launch {
            val data1 = async {
                getTmdbMovieList(TmdbListType.POPULAR_MOVIES)
                    .results
                    .filter { it.poster != null }.map {
                        ImageShowcaseItem(url = it.posterUrl, label = it.title ?: it.originalTitle)
                    }
            }

            val data2 = async {
                ImageCarouselWithTitleData(
                    title = "Top Rated",
                    images = getTmdbMovieList(TmdbListType.TOP_RATED_MOVIES)
                        .results.filter { it.poster != null }
                        .map { it.posterUrl }
                )
            }

            val data3 = async {
                ImageCarouselWithTitleData(
                    title = "Upcoming",
                    images = getTmdbMovieList(TmdbListType.UPCOMING_MOVIES)
                        .results.filter { it.poster != null }
                        .map { it.posterUrl }
                )
            }

            val data4 = async {
                ImageCarouselWithTitleData(
                    title = "Now Playing",
                    images = getTmdbMovieList(TmdbListType.NOW_PLAYING_MOVIES)
                        .results.filter { it.poster != null }
                        .map { it.posterUrl }
                )
            }

            _viewState.value = HomeViewState(
                showcase = data1.await(),
                topRated = data2.await(),
                comingSoon = data3.await(),
                recentReleased = data4.await()
            )
        }
    }

    private fun getGamesData() {
        _viewState.value = null
        viewModelScope.launch {
            val data1 = async {
                getShowcaseGames().gamesList.filter { it.hasCover() && it.name.isNotEmpty() }
                    .map {
                        ImageShowcaseItem(
                            url = "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg",
                            label = it.name
                        )
                    }
            }
            val data2 = async {
                ImageCarouselWithTitleData(
                    title = "Top Rated",
                    images = getTopRatedGames().gamesList.filter { it.hasCover() }
                        .map { "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg" }
                )
            }

            val data3 = async {
                ImageCarouselWithTitleData(
                    title = "Coming Soon",
                    images = getComingSoonGames().gamesList.filter { it.hasCover() }
                        .map { "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg" }
                )
            }

            val data4 = async {
                ImageCarouselWithTitleData(
                    title = "Recently Released",
                    images = getRecentReleasedGames().gamesList.filter { it.hasCover() }
                        .map { "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg" }
                )
            }

            _viewState.value =
                HomeViewState(
                    showcase = data1.await(),
                    topRated = data2.await(),
                    comingSoon = data3.await(),
                    recentReleased = data4.await()
                )
        }
    }
}
