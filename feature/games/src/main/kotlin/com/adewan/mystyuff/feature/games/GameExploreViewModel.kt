package com.adewan.mystyuff.feature.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.common.ux.PosterReelItemData
import com.adewan.mystuff.core.data.repositories.GameRepository
import com.adewan.mystuff.core.models.games.GameQuery
import com.adewan.mystuff.core.models.games.gamesComingInTheNext6Months
import com.adewan.mystuff.core.models.games.gamesReleasedInTheLast2Month
import com.adewan.mystuff.core.models.games.posterUrl
import com.adewan.mystuff.core.models.games.topRatedGamesForLast2Years
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import proto.Game

class GameExploreViewModel(private val repository: GameRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<GameExploreViewState>(GameExploreViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _listViewState = MutableStateFlow<GameListViewState>(GameListViewState.Loading)
    val listViewState = _listViewState.asStateFlow()

    init {
        viewModelScope.launch {
            val data = async {
                repository.getGameListForQuery(
                    topRatedGamesForLast2Years.copy(limit = 9).buildQuery()
                ).map {
                    PosterReelItemData(
                        posterUrl = it.posterUrl(),
                        title = it.name,
                        rating = it.rating,
                        maxRating = it.totalRating,
                        chipsData = it.themesList.take(2).map { theme -> theme.name }
                    )
                }
            }

            val posterGrid1 = async {
                PosterGridData(
                    title = "Coming Soon",
                    games = repository.getGameListForQuery(
                        gamesComingInTheNext6Months.copy(limit = 9).buildQuery()
                    )
                        .sortedByDescending { it.hypes }.take(9),
                    dataQuery = gamesComingInTheNext6Months
                )
            }

            val posterGrid2 = async {
                PosterGridData(
                    title = "Recently Released",
                    games = repository.getGameListForQuery(
                        gamesReleasedInTheLast2Month.copy(limit = 9).buildQuery()
                    )
                        .sortedByDescending { it.rating }.take(9),
                    dataQuery = gamesReleasedInTheLast2Month
                )
            }

            val posterGrid3 = async {
                PosterGridData(
                    title = "Critically Acclaimed",
                    games = repository.getGameListForQuery(
                        topRatedGamesForLast2Years.copy(limit = 9).buildQuery()
                    )
                        .sortedByDescending { it.rating }.take(9),
                    dataQuery = topRatedGamesForLast2Years
                )
            }

            _viewState.value =
                GameExploreViewState.Result(
                    posterReelItems = data.await(),
                    posterGrid1 = posterGrid1.await(),
                    posterGrid2 = posterGrid2.await(),
                    posterGrid3 = posterGrid3.await()
                )
        }
    }

    fun getGamesListForQuery(query: String) {
        viewModelScope.launch {
            _listViewState.value =
                GameListViewState.Result(data = repository.getGameListForQuery(query))
        }
    }
}

sealed interface GameExploreViewState {
    object Loading : GameExploreViewState
    data class Result(
        val posterReelItems: List<PosterReelItemData>,
        val posterGrid1: PosterGridData,
        val posterGrid2: PosterGridData,
        val posterGrid3: PosterGridData
    ) : GameExploreViewState
}

sealed interface GameListViewState {
    object Loading : GameListViewState
    data class Result(
        val data: List<Game>
    ) : GameListViewState
}

data class PosterGridData(val title: String, val games: List<Game>, val dataQuery: GameQuery)
