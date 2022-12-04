package com.adewan.mystuff.core.database

import androidx.room.RoomDatabase

@androidx.room.Database(entities = [DBGame::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun dbGameDao(): DBGameDao
}
