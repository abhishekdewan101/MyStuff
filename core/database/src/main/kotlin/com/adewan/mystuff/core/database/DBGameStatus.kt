package com.adewan.mystuff.core.database

import androidx.room.TypeConverter

enum class DBGameStatus {
    WANTED,
    PURCHASED,
    PLAYING,
    LIKED,
    DISLIKED
}

class DBGameStatusConverter {
    @TypeConverter
    fun fromDBGameStatus(status: DBGameStatus) = status.ordinal

    @TypeConverter
    fun toDBGameStatus(ordinal: Int) = enumValues<DBGameStatus>()[ordinal]
}
