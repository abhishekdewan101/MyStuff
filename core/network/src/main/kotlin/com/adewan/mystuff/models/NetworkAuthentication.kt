package com.adewan.mystuff.models

@kotlinx.serialization.Serializable
data class NetworkAuthentication(
    val accessToken: String,
    val expiresIn: Long
)
