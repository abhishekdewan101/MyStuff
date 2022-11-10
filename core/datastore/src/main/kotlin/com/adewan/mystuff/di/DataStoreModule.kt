package com.adewan.mystuff.di

import android.content.Context
import com.adewan.mystuff.datastore.DataSource
import com.adewan.mystuff.datastore.PreferenceDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single<DataSource> {
        PreferenceDataSource(
            androidContext().getSharedPreferences(
                "MY_STUFF_SHARED_PREFS",
                Context.MODE_PRIVATE
            )
        )
    }
}
