package com.adewan.mystuff.core.repository

import com.adewan.mystuff.core.local.PreferenceDataSource
import com.adewan.mystuff.core.model.LocalIgdbAuthenticationToken
import com.adewan.mystuff.core.model.isValid
import com.adewan.mystuff.core.network.NetworkDataSource
import java.time.Clock
import timber.log.Timber

interface AuthenticationRepository {
    suspend fun initializeIgdbAuthenticationToken(): Boolean
}

class AuthenticationRepositoryImpl(
    private val preferenceDataSource: PreferenceDataSource,
    private val networkDataSource: NetworkDataSource,
    private val clock: Clock,
) : AuthenticationRepository {
    override suspend fun initializeIgdbAuthenticationToken(): Boolean {
        val currentToken = preferenceDataSource.getIgdbToken()
        return if (currentToken != null && currentToken.isValid(clock.millis())) {
            Timber.d("Token is present and valid ")
            true
        } else {
            Timber.d("Getting Igdb token from network")
            val networkToken = networkDataSource.getIgdbAuthenticationToken()
            preferenceDataSource.writeIgdbToken(
                LocalIgdbAuthenticationToken(
                    token = networkToken.token,
                    expiration = networkToken.expiresIn + clock.millis(),
                ),
            )
            true
        }
    }
}
