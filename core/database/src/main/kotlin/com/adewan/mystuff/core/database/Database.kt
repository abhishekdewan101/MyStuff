package com.adewan.mystuff.core.database

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@androidx.room.Database(entities = [DBGame::class], version = 2)
@TypeConverters(DBGameStatusConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun dbGameDao(): DBGameDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE DBGAME ADD COLUMN game_status INTEGER NOT NULL DEFAULT 0")
    }
}
