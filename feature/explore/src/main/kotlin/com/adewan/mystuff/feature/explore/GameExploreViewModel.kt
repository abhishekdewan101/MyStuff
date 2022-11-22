package com.adewan.mystuff.feature.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.common.ux.PosterReelItemData
import com.adewan.mystuff.core.data.repositories.GameRepository
import com.adewan.mystuff.core.models.games.posterUrl
import com.adewan.mystuff.core.models.games.topRatedGamesForLast12Months
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameExploreViewModel(private val repository: GameRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<GameExploreViewState>(GameExploreViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            val data = repository.getGameListForQuery(topRatedGamesForLast12Months).map {
                PosterReelItemData(
                    posterUrl = it.posterUrl(),
                    title = it.name,
                    rating = it.rating,
                    maxRating = it.totalRating,
                    chipsData = it.themesList.take(2).map { theme -> theme.name }
                )
            }
            _viewState.value = GameExploreViewState.Result(posterReelItems = data)
        }
    }
}

sealed interface GameExploreViewState {
    object Loading : GameExploreViewState
    data class Result(val posterReelItems: List<PosterReelItemData>) : GameExploreViewState
}
