package com.adewan.mystuff.core.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * DBGame
 *  |- slug
 *  |- name
 *  |- poster
 *  |- status
 */

@Entity
data class DBGame(
    @ColumnInfo(name = "game_name") val name: String,
    @ColumnInfo(name = "game_poster") val poster: String,
    @PrimaryKey
    @ColumnInfo(name = "game_slug")
    val slug: String,
    @ColumnInfo(name = "game_status") val status: DBGameStatus
)
