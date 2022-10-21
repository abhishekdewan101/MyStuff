package com.adewan.mystuff.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.model.COMING_SOON_GAMES_QUERY
import com.adewan.mystuff.core.model.RECENT_RELEASED_GAMES_QUERY
import com.adewan.mystuff.core.model.SHOWCASE_GAMES_QUERY
import com.adewan.mystuff.core.model.TOP_RATED_GAMES_QUERY
import com.adewan.mystuff.core.model.TmdbListType
import com.adewan.mystuff.core.usecase.GetGamesPosterList
import com.adewan.mystuff.core.usecase.GetTmdbMovieList
import com.adewan.mystuff.core.usecase.GetTmdbShowList
import com.adewan.mystuff.ui.composables.ImageCarouselWithTitleData
import com.adewan.mystuff.ui.composables.ImageShowcaseItem
import com.adewan.mystuff.ui.navigation.NavigationDirector
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
    private val getGamesPosterList: GetGamesPosterList,
    private val getTmdbMovieList: GetTmdbMovieList,
    private val getTmdbShowList: GetTmdbShowList
) : ViewModel() {
    private val _viewState = MutableStateFlow<HomeViewState?>(null)
    val viewState = _viewState.asStateFlow()

    private val _currentFilter = MutableStateFlow(DataFilter.Games)
    val currentFilter = _currentFilter.asStateFlow()

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

    fun handleNavigation(navigationDirector: NavigationDirector, slug: String) {
        when (currentFilter.value) {
            DataFilter.Games -> navigationDirector.navigateToGameDetails(slug)
            else -> {}
        }
    }

    private fun getTvData() {
        _viewState.value = null
        viewModelScope.launch {
            val data1 = async {
                getTmdbShowList(TmdbListType.ON_AIR_TV_SHOWS)
                    .results
                    .filter { it.poster != null }
                    .map {
                        ImageShowcaseItem(
                            identifier = "",
                            url = it.posterUrl,
                            label = it.name ?: it.originalName
                        )
                    }
            }

            val data2 = async {
                getTmdbShowList(TmdbListType.TOP_RATED_TV_SHOWS).run {
                    ImageCarouselWithTitleData(
                        title = "Top Rated",
                        images = results.filter { it.poster != null }.map { it.posterUrl },
                        identifier = results.map { it.id.toString() }
                    )
                }
            }

            val data3 = async {
                getTmdbShowList(TmdbListType.POPULAR_TV_SHOWS).run {
                    ImageCarouselWithTitleData(
                        title = "Popular",
                        images = results.filter { it.poster != null }.map { it.posterUrl },
                        identifier = results.map { it.id.toString() }
                    )
                }
            }

            val data4 = async {
                getTmdbShowList(TmdbListType.AIRING_TODAY_TV_SHOWS).run {
                    ImageCarouselWithTitleData(
                        title = "Airing Today",
                        images = results.filter { it.poster != null }.map { it.posterUrl },
                        identifier = results.map { it.id.toString() }
                    )
                }
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
                        ImageShowcaseItem(
                            identifier = "",
                            url = it.posterUrl,
                            label = it.title ?: it.originalTitle
                        )
                    }
            }

            val data2 = async {
                getTmdbMovieList(TmdbListType.TOP_RATED_MOVIES).run {
                    ImageCarouselWithTitleData(
                        title = "Top Rated",
                        images = results.filter { it.poster != null }.map { it.posterUrl },
                        identifier = results.map { it.id.toString() }
                    )
                }
            }

            val data3 = async {
                getTmdbMovieList(TmdbListType.UPCOMING_MOVIES).run {
                    ImageCarouselWithTitleData(
                        title = "Upcoming",
                        images = results.filter { it.poster != null }.map { it.posterUrl },
                        identifier = results.map { it.id.toString() }
                    )
                }
            }

            val data4 = async {
                getTmdbMovieList(TmdbListType.NOW_PLAYING_MOVIES).run {
                    ImageCarouselWithTitleData(
                        title = "Now Playing",
                        images = results.filter { it.poster != null }.map { it.posterUrl },
                        identifier = results.map { it.id.toString() }
                    )
                }
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
                getGamesPosterList(SHOWCASE_GAMES_QUERY).gamesList.filter { it.hasCover() && it.name.isNotEmpty() }
                    .map {
                        ImageShowcaseItem(
                            identifier = it.slug,
                            url = "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg",
                            label = it.name
                        )
                    }
            }
            val data2 = async {
                getGamesPosterList(TOP_RATED_GAMES_QUERY).gamesList.run {
                    ImageCarouselWithTitleData(
                        title = "Top Rated",
                        images = filter { it.hasCover() }.map { "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg" },
                        identifier = map { it.slug }
                    )
                }
            }

            val data3 = async {
                getGamesPosterList(COMING_SOON_GAMES_QUERY).gamesList.run {
                    ImageCarouselWithTitleData(
                        title = "Coming Soon",
                        images = filter { it.hasCover() }.map { "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg" },
                        identifier = map { it.slug }
                    )
                }
            }

            val data4 = async {
                getGamesPosterList(RECENT_RELEASED_GAMES_QUERY).gamesList.run {
                    ImageCarouselWithTitleData(
                        title = "Recently Released",
                        images = filter { it.hasCover() }.map { "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg" },
                        identifier = map { it.slug }
                    )
                }
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
