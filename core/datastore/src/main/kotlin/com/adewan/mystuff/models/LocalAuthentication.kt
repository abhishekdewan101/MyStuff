package com.adewan.mystuff.models

@kotlinx.serialization.Serializable
data class LocalAuthentication(val accessToken: String, val expiresIn: Long)

fun LocalAuthentication.isValid(timeInMillis: Long) = timeInMillis <= expiresIn
