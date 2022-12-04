package com.adewan.mystuff.core.data.repositories

import com.adewan.mystuff.core.database.DBGame
import com.adewan.mystuff.core.database.DBGameDao
import com.adewan.mystuff.core.models.games.posterUrl
import com.adewan.mystuff.datastore.LocalDataStore
import com.adewan.mystuff.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import proto.Game

interface GameRepository {
    suspend fun getGameListForQuery(query: String): List<Game>
    suspend fun getGameForQuery(query: String): Game
    fun getAllDBGames(): Flow<List<DBGame>>
    fun isGamePresentInDB(slug: String): Boolean
    fun addGameToLibrary(game: Game)
}

class GameRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    dataStore: LocalDataStore,
    private val dbGameDao: DBGameDao
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

    override fun getAllDBGames(): Flow<List<DBGame>> {
        return dbGameDao.getAllGames()
    }

    override fun isGamePresentInDB(slug: String): Boolean {
        val game = dbGameDao.findGame(slug = slug)
        return game != null
    }

    override fun addGameToLibrary(game: Game) {
        val dbGame = DBGame(name = game.name, slug = game.slug, poster = game.posterUrl())
        dbGameDao.insertGame(dbGame)
    }
}
