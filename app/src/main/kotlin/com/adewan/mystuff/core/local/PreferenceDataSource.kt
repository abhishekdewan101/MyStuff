package com.adewan.mystuff.core.local

import android.content.SharedPreferences
import com.adewan.mystuff.core.model.LocalIgdbAuthenticationToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val IGDB_TOKEN = "LOCAL_AUTHENTICATION_KEY"

class PreferenceDataSource(private val sharedPreferences: SharedPreferences) {
    fun getIgdbToken(): LocalIgdbAuthenticationToken? {
        val currentTokenString = sharedPreferences.getString(IGDB_TOKEN, null) ?: return null
        return Json.decodeFromString(currentTokenString)
    }

    fun writeIgdbToken(token: LocalIgdbAuthenticationToken) {
        sharedPreferences.edit().putString(IGDB_TOKEN, Json.encodeToString(token)).apply()
    }
}
