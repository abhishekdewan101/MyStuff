package com.adewan.mystuff.feature.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.data.repositories.GameRepository
import com.adewan.mystuff.core.models.games.posterUrl
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
            val featuredGames = async {
                repository.getGameListForQuery(
                    topRatedGamesForLast2Years.copy(limit = 9).buildQuery()
                ).sortedByDescending { it.rating }
                    .map {
                        FeaturedGames(
                            poster = it.posterUrl(),
                            name = it.name,
                            rating = it.rating,
                            totalRating = it.totalRating,
                            genreString = it.themesList.joinToString(" · ") { theme -> theme.name }
                        )
                    }
            }
            _viewState.value = ExploreViewState.Results(featuredGames = featuredGames.await())
        }
    }
}

sealed interface ExploreViewState {
    object Loading : ExploreViewState
    data class Results(val featuredGames: List<FeaturedGames>) : ExploreViewState
}

data class FeaturedGames(
    val poster: String,
    val rating: Double,
    val name: String,
    val totalRating: Double,
    val genreString: String
)
