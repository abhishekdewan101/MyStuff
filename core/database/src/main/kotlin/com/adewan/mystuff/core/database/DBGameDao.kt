package com.adewan.mystuff.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DBGameDao {
    @Query("SELECT * FROM dbgame")
    fun getAllGames(): Flow<List<DBGame>>

    @Insert
    fun insertGame(game: DBGame)

    @Query("SELECT * from dbgame where game_slug = :slug")
    fun findGame(slug: String): DBGame?
}
