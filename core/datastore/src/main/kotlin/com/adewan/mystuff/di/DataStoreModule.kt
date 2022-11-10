package com.adewan.mystuff.di

import android.content.Context
import com.adewan.mystuff.datastore.LocalDataStore
import com.adewan.mystuff.datastore.PreferenceDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single<LocalDataStore> {
        PreferenceDataStore(
            androidContext().getSharedPreferences(
                "MY_STUFF_SHARED_PREFS",
                Context.MODE_PRIVATE
            )
        )
    }
}
