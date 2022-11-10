package com.adewan.mystuff.core.data.di

import com.adewan.mystuff.core.data.repositories.AuthenticationRepository
import com.adewan.mystuff.core.data.repositories.IgdbAuthenticationRepository
import com.adewan.mystuff.datastore.DataSource
import com.adewan.mystuff.network.NetworkDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get
import java.time.Clock

val dataModule = module {
    singleOf<AuthenticationRepository> {
        IgdbAuthenticationRepository(
            get(NetworkDataSource::class.java),
            get(DataSource::class.java),
            Clock.systemDefaultZone()
        )
    }
}
