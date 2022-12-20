package com.adewan.mystuff.core.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), Database::class.java, "MyStuffDB")
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    single {
        val db: Database = get()
        return@single db.dbGameDao()
    }
}
