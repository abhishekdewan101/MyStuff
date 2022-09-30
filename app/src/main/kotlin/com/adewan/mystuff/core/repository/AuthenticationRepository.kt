package com.adewan.mystuff.core.repository

import com.adewan.mystuff.core.local.PreferenceDataSource
import com.adewan.mystuff.core.model.LocalIgdbAuthenticationToken
import com.adewan.mystuff.core.model.isValid
import com.adewan.mystuff.core.network.NetworkDataSource
import timber.log.Timber
import java.time.Clock

class AuthenticationRepository(
    private val preferenceDataSource: PreferenceDataSource,
    private val networkDataSource: NetworkDataSource,
    private val clock: Clock
) {
    suspend fun provideIgdbAuthenticationToken(): String {
        val currentToken = preferenceDataSource.getIgdbToken()
        return if (currentToken != null && currentToken.isValid(clock.millis())) {
            Timber.d("Getting Igdb token from cache")
            currentToken.token
        } else {
            Timber.d("Getting Igdb token from network")
            val networkToken = networkDataSource.getIgdbAuthenticationToken()
            preferenceDataSource.writeIgdbToken(
                LocalIgdbAuthenticationToken(
                    token = networkToken.token,
                    expiration = networkToken.expiresIn + clock.millis()
                )
            )
            networkToken.token
        }
    }
}
