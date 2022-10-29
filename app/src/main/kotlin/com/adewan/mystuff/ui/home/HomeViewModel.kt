package com.adewan.mystuff.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.model.COMING_SOON_GAMES_QUERY
import com.adewan.mystuff.core.model.RECENT_RELEASED_GAMES_QUERY
import com.adewan.mystuff.core.model.SHOWCASE_GAMES_QUERY
import com.adewan.mystuff.core.model.TOP_RATED_GAMES_QUERY
import com.adewan.mystuff.core.model.TmdbListType
import com.adewan.mystuff.core.repository.IgdbRepository
import com.adewan.mystuff.core.repository.TmdbRepository
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

enum class HomeViewFilters {
    Games,
    Movies,
    Tv
}

class HomeViewModel(
    private val igdbRepository: IgdbRepository,
    private val tmdbRepository: TmdbRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow<HomeViewState?>(null)
    val viewState = _viewState.asStateFlow()

    private val _currentFilter = MutableStateFlow(HomeViewFilters.Games)
    private val currentFilter = _currentFilter.asStateFlow()

    init {
        viewModelScope.launch {
            _currentFilter.collect {
                when (it) {
                    HomeViewFilters.Games -> getGamesData()
                    HomeViewFilters.Movies -> getMoviesData()
                    HomeViewFilters.Tv -> getTvData()
                }
            }
        }
    }

    fun changeFilter(filter: HomeViewFilters) {
        _currentFilter.value = filter
    }

    fun handleNavigation(navigationDirector: NavigationDirector, identifier: String) {
        when (currentFilter.value) {
            HomeViewFilters.Games -> navigationDirector.navigateToGameDetails(identifier)
            HomeViewFilters.Movies -> navigationDirector.navigateToMovieDetails(identifier)
            HomeViewFilters.Tv -> navigationDirector.navigateToTvShowDetails(identifier)
        }
    }

    private fun getTvData() {
        _viewState.value = null
        viewModelScope.launch {
            val data1 = async {
                tmdbRepository.getTmdbTvShowList(TmdbListType.ON_AIR_TV_SHOWS)
                    .results
                    .filter { it.poster != null }
                    .map {
                        ImageShowcaseItem(
                            identifier = it.id.toString(),
                            url = it.posterUrl,
                            label = it.name ?: it.originalName
                        )
                    }
            }

            val data2 = async {
                tmdbRepository.getTmdbTvShowList(TmdbListType.TOP_RATED_TV_SHOWS).run {
                    ImageCarouselWithTitleData(
                        title = "Top Rated",
                        images = results.filter { it.poster != null }.map { it.posterUrl },
                        identifier = results.map { it.id.toString() }
                    )
                }
            }

            val data3 = async {
                tmdbRepository.getTmdbTvShowList(TmdbListType.POPULAR_TV_SHOWS).run {
                    ImageCarouselWithTitleData(
                        title = "Popular",
                        images = results.filter { it.poster != null }.map { it.posterUrl },
                        identifier = results.map { it.id.toString() }
                    )
                }
            }

            val data4 = async {
                tmdbRepository.getTmdbTvShowList(TmdbListType.AIRING_TODAY_TV_SHOWS).run {
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
                tmdbRepository.getTmdbMovieList(TmdbListType.POPULAR_MOVIES)
                    .results
                    .filter { it.poster != null }.map {
                        ImageShowcaseItem(
                            identifier = it.id.toString(),
                            url = it.posterUrl,
                            label = it.title ?: it.originalTitle
                        )
                    }
            }

            val data2 = async {
                tmdbRepository.getTmdbMovieList(TmdbListType.TOP_RATED_MOVIES).run {
                    ImageCarouselWithTitleData(
                        title = "Top Rated",
                        images = results.filter { it.poster != null }.map { it.posterUrl },
                        identifier = results.map { it.id.toString() }
                    )
                }
            }

            val data3 = async {
                tmdbRepository.getTmdbMovieList(TmdbListType.UPCOMING_MOVIES).run {
                    ImageCarouselWithTitleData(
                        title = "Upcoming",
                        images = results.filter { it.poster != null }.map { it.posterUrl },
                        identifier = results.map { it.id.toString() }
                    )
                }
            }

            val data4 = async {
                tmdbRepository.getTmdbMovieList(TmdbListType.NOW_PLAYING_MOVIES).run {
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
                igdbRepository.getGameCovers(SHOWCASE_GAMES_QUERY).gamesList.filter { it.hasCover() && it.name.isNotEmpty() }
                    .map {
                        ImageShowcaseItem(
                            identifier = it.slug,
                            url = "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg",
                            label = it.name
                        )
                    }
            }
            val data2 = async {
                igdbRepository.getGameCovers(TOP_RATED_GAMES_QUERY).gamesList.run {
                    ImageCarouselWithTitleData(
                        title = "Top Rated",
                        images = filter { it.hasCover() }.map { "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg" },
                        identifier = map { it.slug }
                    )
                }
            }

            val data3 = async {
                igdbRepository.getGameCovers(COMING_SOON_GAMES_QUERY).gamesList.run {
                    ImageCarouselWithTitleData(
                        title = "Coming Soon",
                        images = filter { it.hasCover() }.map { "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.jpg" },
                        identifier = map { it.slug }
                    )
                }
            }

            val data4 = async {
                igdbRepository.getGameCovers(RECENT_RELEASED_GAMES_QUERY).gamesList.run {
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
