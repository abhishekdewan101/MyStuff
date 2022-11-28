package com.adewan.mystuff.feature.expanded

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.data.repositories.GameRepository
import com.adewan.mystuff.core.models.games.posterUrl
import com.adewan.mystuff.core.models.navigation.ExpandedViewArgs
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExpandedViewModel(private val repository: GameRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<ExpandedViewState>(ExpandedViewState.Loading)
    val viewState = _viewState.asStateFlow()

    fun loadList(args: ExpandedViewArgs) {
        viewModelScope.launch {
            _viewState.value = ExpandedViewState.Loading
            try {
                val result = async {
                    repository.getGameListForQuery(args.query)
                        .sortedByDescending { it.firstReleaseDate.nanos }
                        .map {
                            PosterItem(slug = it.slug, poster = it.posterUrl())
                        }
                }
                _viewState.value = ExpandedViewState.Results(result = result.await())
            } catch (e: Exception) {
                _viewState.value = ExpandedViewState.Error
            }
        }
    }
}

sealed interface ExpandedViewState {
    object Loading : ExpandedViewState
    object Error : ExpandedViewState
    data class Results(val result: List<PosterItem>) : ExpandedViewState
}

data class PosterItem(val slug: String, val poster: String)
