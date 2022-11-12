package com.adewan.mystuff.core.data.repositories

import com.adewan.mystuff.datastore.LocalDataStore
import com.adewan.mystuff.network.NetworkDataSource
import proto.Game

interface GameRepository {
    suspend fun getGameListForQuery(query: String): List<Game>
    suspend fun getGameForQuery(query: String): Game
}

class GameRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    dataStore: LocalDataStore
) : GameRepository {
    private val token: String by lazy {
        val localAuthentication = dataStore.getLocalAuthentication() ?: throw IllegalStateException(
            "Local authentication shouldn't be null when accessing GameRepository"
        )
        localAuthentication.accessToken
    }

    override suspend fun getGameListForQuery(query: String): List<Game> {
        return networkDataSource.getGamesForQuery(token = token, query = query).gamesList
    }

    override suspend fun getGameForQuery(query: String): Game {
        return networkDataSource.getGamesForQuery(token = token, query = query).gamesList.first()
    }
}