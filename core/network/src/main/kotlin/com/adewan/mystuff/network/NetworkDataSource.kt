package com.adewan.mystuff.network

import com.adewan.mystuff.models.DataSourceCredentials
import com.adewan.mystuff.models.NetworkAuthentication
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.takeFrom

interface NetworkDataSource {
    suspend fun authenticateAndReturnAuthentication(): NetworkAuthentication
}

class KtorNetworkDataSource(
    private val client: HttpClient,
    credentials: DataSourceCredentials
) : NetworkDataSource {
    override suspend fun authenticateAndReturnAuthentication(): NetworkAuthentication {
        return client.get {
            url {
                takeFrom("https://px058nbguc.execute-api.us-east-1.amazonaws.com/default/authentication")
            }
        }.body()
    }
}
