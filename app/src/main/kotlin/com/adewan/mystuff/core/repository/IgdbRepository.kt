package com.adewan.mystuff.core.repository

import com.adewan.mystuff.BuildConfig
import com.adewan.mystuff.core.model.buildGameDetailQuery
import com.adewan.mystuff.core.network.NetworkDataSource
import com.adewan.mystuff.datastore.LocalDataStore
import proto.Game
import proto.GameResult

class IgdbRepository(
    private val networkDataSource: NetworkDataSource,
    private val dataSource: LocalDataStore,
    private val clientId: String = BuildConfig.ClientId,
) {
    suspend fun getGameCovers(forQuery: String): GameResult {
        val token = dataSource.getLocalAuthentication()?.accessToken
            ?: throw IllegalStateException("Cannot get games if you don't have a igdb token")
        return networkDataSource.requestGames(
            clientId = clientId,
            token = token,
            forQuery = forQuery,
        )
    }

    suspend fun getGameDetails(forIdentifier: String): Game {
        val token = dataSource.getLocalAuthentication()?.accessToken
            ?: throw IllegalStateException("Cannot get games if you don't have a igdb token")
        return networkDataSource.requestGames(
            clientId = clientId,
            token = token,
            forQuery = buildGameDetailQuery(slug = forIdentifier),
        ).gamesList.first()
    }
}
