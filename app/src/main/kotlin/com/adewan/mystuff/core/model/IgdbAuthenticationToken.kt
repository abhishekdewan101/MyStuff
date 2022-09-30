package com.adewan.mystuff.core.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class IgdbAuthenticationToken(
    @SerialName("accessToken") val token: String,
    val expiresIn: Long
)