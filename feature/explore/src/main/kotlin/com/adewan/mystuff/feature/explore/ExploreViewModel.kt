package com.adewan.mystuff.feature.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.data.repositories.GameRepository
import com.adewan.mystuff.core.models.games.GameQuery
import com.adewan.mystuff.core.models.games.gamesComingInTheNext6Months
import com.adewan.mystuff.core.models.games.gamesReleasedInTheLast2Month
import com.adewan.mystuff.core.models.games.openWorldGames
import com.adewan.mystuff.core.models.games.posterUrl
import com.adewan.mystuff.core.models.games.scienceFictionGames
import com.adewan.mystuff.core.models.games.topRatedGamesForLast2Years
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExploreViewModel(private val repository: GameRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<ExploreViewState>(ExploreViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            _viewState.value = ExploreViewState.Loading
            try {
                val featuredPosterItem = async {
                    repository.getGameListForQuery(
                        topRatedGamesForLast2Years.copy(limit = 9).buildQuery()
                    ).sortedByDescending { it.rating }
                        .map {
                            FeaturedPosterItem(
                                poster = it.posterUrl(),
                                name = it.name,
                                slug = it.slug,
                                rating = it.rating,
                                totalRating = it.totalRating,
                                genreString = it.themesList.joinToString(" · ") { theme -> theme.name }
                            )
                        }
                }

                val posterItems1 = async {
                    repository.getGameListForQuery(
                        gamesComingInTheNext6Months.copy(limit = 9).buildQuery()
                    ).sortedByDescending { it.hypes }.map {
                        PosterItem(slug = it.slug, poster = it.posterUrl())
                    }
                }

                val posterItems2 = async {
                    repository.getGameListForQuery(
                        gamesReleasedInTheLast2Month.copy(limit = 9).buildQuery()
                    ).sortedByDescending { it.rating }.map {
                        PosterItem(slug = it.slug, poster = it.posterUrl())
                    }
                }

                val posterItems3 = async {
                    repository.getGameListForQuery(
                        openWorldGames.copy(limit = 9).buildQuery()
                    ).sortedByDescending { it.firstReleaseDate.seconds }.map {
                        PosterItem(slug = it.slug, poster = it.posterUrl())
                    }
                }

                val posterItems4 = async {
                    repository.getGameListForQuery(scienceFictionGames.copy(limit = 9).buildQuery())
                        .sortedByDescending { it.firstReleaseDate.seconds }
                        .map {
                            PosterItem(slug = it.slug, poster = it.posterUrl())
                        }
                }

                _viewState.value =
                    ExploreViewState.Results(
                        featurePosters = featuredPosterItem.await(),
                        grid1 = PosterViewItem(
                            "Coming soon",
                            posterItems1.await(),
                            gamesComingInTheNext6Months
                        ),
                        grid2 = PosterViewItem(
                            "Recently Released",
                            posterItems2.await(),
                            gamesReleasedInTheLast2Month
                        ),
                        grid3 = PosterViewItem(
                            "Open World Games",
                            posterItems3.await(),
                            openWorldGames
                        ),
                        grid4 = PosterViewItem(
                            "Science Fiction Games",
                            posterItems4.await(),
                            scienceFictionGames
                        )
                    )
            } catch (e: Exception) {
                _viewState.value = ExploreViewState.Error
            }
        }
    }
}

sealed interface ExploreViewState {
    object Loading : ExploreViewState
    object Error : ExploreViewState
    data class Results(
        val featurePosters: List<FeaturedPosterItem>,
        val grid1: PosterViewItem,
        val grid2: PosterViewItem,
        val grid3: PosterViewItem,
        val grid4: PosterViewItem
    ) : ExploreViewState
}

data class PosterItem(val slug: String, val poster: String)

data class PosterViewItem(val first: String, val second: List<PosterItem>, val third: GameQuery)

data class FeaturedPosterItem(
    val slug: String,
    val poster: String,
    val rating: Double,
    val name: String,
    val totalRating: Double,
    val genreString: String
)
