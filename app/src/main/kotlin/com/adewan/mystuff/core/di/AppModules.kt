package com.adewan.mystuff.core.di

import android.content.Context
import com.adewan.mystuff.core.network.NetworkDataSource
import com.adewan.mystuff.core.repository.TmdbRepository
import java.time.Clock
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * AppModule defines app level declarations of dependencies that can be used everywhere in the app.
 */
val appModule = module {
    single {
        androidContext().getSharedPreferences("MY_STUFF_SHARED_PREFS", Context.MODE_PRIVATE)
    }

    single {
        Clock.systemDefaultZone()
    }

    // Repositories
    single { TmdbRepository(get()) }

    // DataSources
    single { NetworkDataSource(get()) }
}
