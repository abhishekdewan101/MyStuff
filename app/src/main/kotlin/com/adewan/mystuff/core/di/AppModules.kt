package com.adewan.mystuff.core.di

import android.content.Context
import com.adewan.mystuff.BuildConfig
import com.adewan.mystuff.core.local.PreferenceDataSource
import com.adewan.mystuff.core.network.NetworkDataSource
import com.adewan.mystuff.core.repository.AuthenticationRepository
import com.adewan.mystuff.core.repository.IgdbRepository
import com.adewan.mystuff.core.repository.TmdbRepository
import com.adewan.mystuff.ui.gamedetails.GameDetailViewModel
import com.adewan.mystuff.ui.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.ContentConverter
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.charsets.Charset
import io.ktor.utils.io.core.readBytes
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import proto.GameResult
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
                register(ContentType.Application.ProtoBuf, ProtobufConverter())
            }
            install(HttpCache)
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

    // ViewModel
    viewModel {
        HomeViewModel(get(), get())
    }

    viewModel {
        GameDetailViewModel(get())
    }

    // Repositories
    single { AuthenticationRepository(get(), get(), get()) }
    single { IgdbRepository(get(), get()) }
    single { TmdbRepository(get()) }

    // DataSources
    single { PreferenceDataSource(get()) }
    single { NetworkDataSource(get()) }
}

class ProtobufConverter : ContentConverter {
    override suspend fun deserialize(
        charset: Charset,
        typeInfo: TypeInfo,
        content: ByteReadChannel
    ): Any {
        val byteArray = content.readRemaining().readBytes()
        return GameResult.parseFrom(byteArray)
    }
}
