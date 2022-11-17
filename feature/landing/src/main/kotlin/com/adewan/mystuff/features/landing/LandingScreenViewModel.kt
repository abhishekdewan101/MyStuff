package com.adewan.mystuff.features.landing

import androidx.lifecycle.ViewModel
import com.adewan.mystuff.core.data.repositories.AuthenticationRepository

class LandingScreenViewModel(private val authRepo: AuthenticationRepository) : ViewModel() {
    suspend fun checkAuthentication(whenAuthenticated: () -> Unit) {
        authRepo.getAndSaveAuthenticationToken()
        whenAuthenticated()
    }
}
