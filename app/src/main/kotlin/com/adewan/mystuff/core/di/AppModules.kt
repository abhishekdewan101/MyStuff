package com.adewan.mystuff.core.di

import android.content.Context
import com.adewan.mystuff.BuildConfig
import com.adewan.mystuff.core.local.PreferenceDataSource
import com.adewan.mystuff.core.network.NetworkDataSource
import com.adewan.mystuff.core.repository.AuthenticationRepository
import com.adewan.mystuff.core.repository.GameRepository
import com.adewan.mystuff.core.repository.MovieRepository
import com.adewan.mystuff.core.usecase.GetComingSoonGames
import com.adewan.mystuff.core.usecase.GetComingSoonMovies
import com.adewan.mystuff.core.usecase.GetRecentReleasedGames
import com.adewan.mystuff.core.usecase.GetRecentReleasedMovies
import com.adewan.mystuff.core.usecase.GetShowcaseGames
import com.adewan.mystuff.core.usecase.GetShowcaseMovies
import com.adewan.mystuff.core.usecase.GetTopRatedGames
import com.adewan.mystuff.core.usecase.GetTopRatedMovies
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
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }

    // UseCase
    single { GetShowcaseGames(get()) }
    single { GetTopRatedGames(get()) }
    single { GetComingSoonGames(get()) }
    single { GetRecentReleasedGames(get()) }
    single { GetTopRatedMovies(get()) }
    single { GetComingSoonMovies(get()) }
    single { GetRecentReleasedMovies(get()) }
    single { GetShowcaseMovies(get()) }

    // Repositories
    single { AuthenticationRepository(get(), get(), get()) }
    single { GameRepository(get(), get()) }
    single { MovieRepository(get()) }

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
