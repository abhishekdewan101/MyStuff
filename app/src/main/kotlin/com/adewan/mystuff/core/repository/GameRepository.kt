package com.adewan.mystuff.core.repository

import com.adewan.mystuff.BuildConfig
import com.adewan.mystuff.core.local.PreferenceDataSource
import com.adewan.mystuff.core.network.NetworkDataSource
import proto.GameResult

class GameRepository(
    private val networkDataSource: NetworkDataSource,
    private val preferenceDataSource: PreferenceDataSource,
    private val clientId: String = BuildConfig.ClientId
) {
    suspend fun getGameCovers(forQuery: String): GameResult {
        val token = preferenceDataSource.getIgdbToken()?.token
            ?: throw IllegalStateException("Cannot get games if you don't have a igdb token")
        return networkDataSource.requestGames(
            clientId = clientId,
            token = token,
            forQuery = forQuery
        )
    }
}