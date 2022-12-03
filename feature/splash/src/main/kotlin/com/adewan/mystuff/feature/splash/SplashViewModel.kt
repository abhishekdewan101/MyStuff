package com.adewan.mystuff.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.mystuff.core.data.repositories.AuthenticationRepository
import com.adewan.mystuff.core.data.repositories.OnBoardingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repository: AuthenticationRepository,
    private val onBoardingRepository: OnBoardingRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow<SplashViewState>(SplashViewState.Initial)
    val viewState = _viewState.asStateFlow()

    fun authenticateUser() {
        _viewState.value = SplashViewState.Loading
        viewModelScope.launch {
            repository.getAndSaveAuthenticationToken()
            _viewState.value = SplashViewState.Authenticated
            onBoardingRepository.onBoardingCompleted()
        }
    }
}

interface SplashViewState {
    object Initial : SplashViewState
    object Loading : SplashViewState
    object Authenticated : SplashViewState
}
