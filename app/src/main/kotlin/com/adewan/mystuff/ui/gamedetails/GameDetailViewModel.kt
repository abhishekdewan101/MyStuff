package com.adewan.mystuff.ui.gamedetails

import androidx.lifecycle.ViewModel
import com.adewan.mystuff.core.repository.IgdbRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import proto.Game

class GameDetailViewModel(private val igdbRepository: IgdbRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<Game?>(null)
    val viewState = _viewState.asStateFlow()

    suspend fun requestGameDetailForIdentifier(identifier: String) {
        _viewState.value = igdbRepository.getGameDetails(forIdentifier = identifier)
    }
}
