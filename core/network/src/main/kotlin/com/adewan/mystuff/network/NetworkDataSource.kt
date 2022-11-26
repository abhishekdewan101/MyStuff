package com.adewan.mystuff.network

import com.adewan.mystuff.models.DataSourceCredentials
import com.adewan.mystuff.models.NetworkAuthentication
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.takeFrom
import proto.GameResult

interface NetworkDataSource {
    suspend fun authenticateAndReturnAuthentication(): NetworkAuthentication
    suspend fun getGamesForQuery(token: String, query: String): GameResult
    suspend fun searchForGame(token: String, query: String): GameResult
}

class KtorNetworkDataSource(
    private val client: HttpClient,
    private val credentials: DataSourceCredentials
) : NetworkDataSource {
    override suspend fun authenticateAndReturnAuthentication(): NetworkAuthentication {
        return client.get {
            url {
                takeFrom("https://px058nbguc.execute-api.us-east-1.amazonaws.com/default/authentication")
            }
        }.body()
    }

    override suspend fun getGamesForQuery(token: String, query: String): GameResult {
        return client.post {
            headers {
                append("Client-ID", credentials.igdbClientId)
                append("Authorization", "Bearer $token")
            }
            url { takeFrom("https://api.igdb.com/v4/games.pb") }
            setBody(query)
        }.body()
    }

    override suspend fun searchForGame(token: String, query: String): GameResult {
        return client.post {
            headers {
                append("Client-ID", credentials.igdbClientId)
                append("Authorization", "Bearer $token")
            }
            url { takeFrom("https://api.igdb.com/v4/games.pb") }
            setBody(query)
        }.body()
    }
}
