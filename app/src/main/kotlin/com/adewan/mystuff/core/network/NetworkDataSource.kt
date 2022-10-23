package com.adewan.mystuff.core.network

import com.adewan.mystuff.BuildConfig
import com.adewan.mystuff.core.model.IgdbAuthenticationToken
import com.adewan.mystuff.core.model.TmdbListType
import com.adewan.mystuff.core.model.TmdbMovie
import com.adewan.mystuff.core.model.TmdbResultList
import com.adewan.mystuff.core.model.TmdbTvShow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.takeFrom
import proto.GameResult

class NetworkDataSource(private val client: HttpClient) {
    suspend fun getIgdbAuthenticationToken(): IgdbAuthenticationToken =
        client.get {
            url {
                takeFrom("https://px058nbguc.execute-api.us-east-1.amazonaws.com/default/authentication")
            }
        }.body()

    suspend fun requestGames(clientId: String, token: String, forQuery: String): GameResult =
        client.post {
            headers {
                append("Client-ID", clientId)
                append("Authorization", "Bearer $token")
            }
            url { takeFrom("https://api.igdb.com/v4/games.pb") }
            setBody(forQuery)
        }.body()

    suspend fun requestTmdbMovieList(tmdbListType: TmdbListType): TmdbResultList<TmdbMovie> {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3${tmdbListType.endpoint}")
                parameter("api_key", BuildConfig.TmdbClientId)
                parameter("language", "en-US")
                parameter("region", "US")
            }
        }.body()
    }

    suspend fun requestTmdbTvShowList(tmdbListType: TmdbListType): TmdbResultList<TmdbTvShow> {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3${tmdbListType.endpoint}")
                parameter("api_key", BuildConfig.TmdbClientId)
                parameter("language", "en-US")
                parameter("region", "US")
            }
        }.body()
    }

    suspend fun requestTmdbMovieDetails(identifier: String): TmdbMovie {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3/movie/$identifier")
                parameter("api_key", BuildConfig.TmdbClientId)
                parameter("language", "en-US")
                parameter("region", "US")
            }
        }.body()
    }
}
