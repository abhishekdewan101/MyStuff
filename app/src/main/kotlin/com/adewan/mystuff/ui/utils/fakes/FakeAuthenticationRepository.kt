package com.adewan.mystuff.ui.utils.fakes

import com.adewan.mystuff.core.data.repositories.AuthenticationRepository

class FakeAuthenticationRepository : AuthenticationRepository {
    var authenticationState: Boolean = false

    override suspend fun getAndSaveAuthenticationToken(): Boolean {
        return authenticationState
    }
}
