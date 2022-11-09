package com.adewan.mystuff.core.models.igdb

import proto.Authentication
import proto.authentication

@kotlinx.serialization.Serializable
data class IgdbAuthentication(val accessToken: String, val expiresIn: Long)

fun IgdbAuthentication.toAuthentication(currentTimeInMilli: Long): Authentication {
    return authentication {
        this.accessToken = this@toAuthentication.accessToken
        this.expiresIn = currentTimeInMilli + this@toAuthentication.expiresIn
    }
}

fun Authentication.isValid(currentTimeInMilli: Long): Boolean = currentTimeInMilli <= expiresIn
