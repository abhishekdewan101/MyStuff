package com.adewan.mystuff.ui.utils.fakes

import com.adewan.mystuff.core.repository.AuthenticationRepository

class FakeAuthenticationRepository : AuthenticationRepository {
    var authenticationState: Boolean = false
    override suspend fun initializeIgdbAuthenticationToken(): Boolean {
        return authenticationState
    }
}
