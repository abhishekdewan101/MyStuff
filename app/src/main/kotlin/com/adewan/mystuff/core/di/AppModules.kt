package com.adewan.mystuff.core.di

import android.content.Context
import com.adewan.mystuff.BuildConfig
import com.adewan.mystuff.core.local.PreferenceDataSource
import com.adewan.mystuff.core.network.NetworkDataSource
import com.adewan.mystuff.core.repository.AuthenticationRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.time.Clock

/**
 * AppModule defines app level declarations of dependencies that can be used everywhere in the app.
 */

val appModule = module {
    singleOf<HttpClient> {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                logger = object :
                    Logger {
                    override fun log(message: String) {
                        if (BuildConfig.DEBUG) println(message)
                    }
                }
                level =
                    if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
            }
        }
    }

    single {
        androidContext().getSharedPreferences("MyStuff_SharedPrefence", Context.MODE_PRIVATE)
    }

    single {
        Clock.systemDefaultZone()
    }

    // Repositories
    single { AuthenticationRepository(get(), get(), get()) }

    // DataSources
    single { PreferenceDataSource(get()) }

    single { NetworkDataSource(get()) }
}
