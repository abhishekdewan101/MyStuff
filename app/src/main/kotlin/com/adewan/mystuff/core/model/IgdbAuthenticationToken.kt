package com.adewan.mystuff.core.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class IgdbAuthenticationToken(
    @SerialName("accessToken") val token: String,
    val expiresIn: Long,
)

@kotlinx.serialization.Serializable
data class LocalIgdbAuthenticationToken(val accessToken: String, val expiresIn: Long)

fun LocalIgdbAuthenticationToken.isValid(currentDateInMillis: Long): Boolean {
    return currentDateInMillis <= expiresIn
}
