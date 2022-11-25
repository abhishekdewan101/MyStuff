package com.adewan.mystuff.ui.gamedetails

import androidx.lifecycle.ViewModel
import com.adewan.mystuff.core.data.repositories.GameRepository
import com.adewan.mystuff.core.models.games.gameDetailsQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import proto.Game

class GameDetailViewModel(private val gameRepository: GameRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<Game?>(null)
    val viewState = _viewState.asStateFlow()

    suspend fun requestGameDetailForIdentifier(identifier: String) {
        _viewState.value =
            gameRepository.getGameForQuery(gameDetailsQuery(identifier).buildQuery())
    }
}
