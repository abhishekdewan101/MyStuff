package com.adewan.mystuff.core.data.repositories

import com.adewan.mystuff.datastore.LocalDataStore
import com.adewan.mystuff.models.LocalAuthentication
import com.adewan.mystuff.models.NetworkAuthentication
import com.adewan.mystuff.models.isValid
import com.adewan.mystuff.network.NetworkDataSource
import java.time.Clock

interface AuthenticationRepository {
    suspend fun getAndSaveAuthenticationToken(): Boolean
}

class IgdbAuthenticationRepository(
    private val networkDataSource: NetworkDataSource,
    private val localDataStore: LocalDataStore,
    private val clock: Clock
) : AuthenticationRepository {
    override suspend fun getAndSaveAuthenticationToken(): Boolean {
        val localAuthentication = localDataStore.getLocalAuthentication()
        return if (localAuthentication != null && localAuthentication.isValid(clock.millis())) {
            true
        } else {
            val networkAuthentication = networkDataSource.authenticateAndReturnAuthentication()
            localDataStore.storeLocalAuthentication(
                networkAuthentication.toLocalAuthentication(
                    clock.millis()
                )
            )
            true
        }
    }
}

private fun NetworkAuthentication.toLocalAuthentication(timeInMillis: Long): LocalAuthentication {
    return LocalAuthentication(
        accessToken = this@toLocalAuthentication.accessToken,
        expiresIn = timeInMillis + this@toLocalAuthentication.expiresIn
    )
}
