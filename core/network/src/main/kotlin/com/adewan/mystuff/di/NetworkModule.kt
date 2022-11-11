package com.adewan.mystuff.di

import com.adewan.mystuff.models.DataSourceCredentials
import com.adewan.mystuff.network.BuildConfig
import com.adewan.mystuff.network.KtorNetworkDataSource
import com.adewan.mystuff.network.NetworkDataSource
import com.adewan.mystuff.network.R
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
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get
import proto.GameResult

val networkModule = module {
    single {
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
                logger = object : Logger {
                    override fun log(message: String) {
                        if (BuildConfig.DEBUG) println(message)
                    }
                }
                level =
                    if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
            }
        }
    }

    single<NetworkDataSource> {
        val resources = androidContext().resources
        KtorNetworkDataSource(
            client = get(HttpClient::class.java),
            credentials = DataSourceCredentials(
                igdbClientId = resources.getString(R.string.clientId),
                tmdbClientId = resources.getString(R.string.tmdbClientId)
            )
        )
    }
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
