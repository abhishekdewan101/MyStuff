package com.adewan.mystuff.core.data.di

import com.adewan.mystuff.core.data.repositories.AuthenticationRepository
import com.adewan.mystuff.core.data.repositories.GameRepository
import com.adewan.mystuff.core.data.repositories.GameRepositoryImpl
import com.adewan.mystuff.core.data.repositories.IgdbAuthenticationRepository
import com.adewan.mystuff.core.data.repositories.OnBoardRepositoryImpl
import com.adewan.mystuff.core.data.repositories.OnBoardingRepository
import com.adewan.mystuff.core.database.DBGameDao
import com.adewan.mystuff.datastore.LocalDataStore
import com.adewan.mystuff.network.NetworkDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get
import java.time.Clock

val dataModule = module {
    singleOf<AuthenticationRepository> {
        IgdbAuthenticationRepository(
            get(NetworkDataSource::class.java),
            get(LocalDataStore::class.java),
            Clock.systemDefaultZone()
        )
    }

    singleOf<GameRepository> {
        GameRepositoryImpl(
            get(NetworkDataSource::class.java),
            get(LocalDataStore::class.java),
            get(DBGameDao::class.java)
        )
    }

    singleOf<OnBoardingRepository> {
        OnBoardRepositoryImpl(
            get(LocalDataStore::class.java)
        )
    }
}
