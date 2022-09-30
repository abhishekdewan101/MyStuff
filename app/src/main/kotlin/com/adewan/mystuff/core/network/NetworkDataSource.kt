package com.adewan.mystuff.core.network

import com.adewan.mystuff.core.model.IgdbAuthenticationToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.takeFrom

interface NetworkDataSource {
    suspend fun getIgdbAuthenticationToken(): IgdbAuthenticationToken
}

class NetworkDataSourceImpl(private val client: HttpClient) : NetworkDataSource {
    override suspend fun getIgdbAuthenticationToken(): IgdbAuthenticationToken =
        client.get {
            url {
                takeFrom("https://px058nbguc.execute-api.us-east-1.amazonaws.com/default/authentication")
            }
        }.body()
}
