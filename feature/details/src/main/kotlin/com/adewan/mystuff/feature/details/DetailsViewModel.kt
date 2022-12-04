package com.adewan.mystuff.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.data.repositories.GameRepository
import com.adewan.mystuff.core.models.games.buildDetailsQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import proto.Game

class DetailsViewModel(private val repository: GameRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<DetailsViewState>(DetailsViewState.Loading)
    val viewState = _viewState.asStateFlow()

    fun loadDetails(id: String) {
        viewModelScope.launch {
            _viewState.value = DetailsViewState.Loading

            val data =
                async { repository.getGameForQuery(buildDetailsQuery(slug = id).buildQuery()) }
            val isGamePresentInDB =
                async(Dispatchers.IO) { repository.isGamePresentInDB(slug = id) }

            _viewState.value =
                DetailsViewState.Result(
                    data = data.await(),
                    isGamePresentInDB = isGamePresentInDB.await()
                )
        }
    }

    fun addGameToLibrary() {
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                val result = viewState.value as? DetailsViewState.Result
                result?.let {
                    repository.addGameToLibrary(it.data)
                    _viewState.value = it.copy(isGamePresentInDB = true)
                }
            }
        }
    }
}

interface DetailsViewState {
    object Loading : DetailsViewState
    object Error : DetailsViewState
    data class Result(val data: Game, val isGamePresentInDB: Boolean) : DetailsViewState
}
