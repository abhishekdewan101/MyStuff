package com.adewan.mystuff.datastore

import android.content.SharedPreferences
import com.adewan.mystuff.models.LocalAuthentication
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface DataSource {
    suspend fun storeLocalAuthentication(localAuthentication: LocalAuthentication)
    suspend fun getLocalAuthentication(): LocalAuthentication?
}

private const val LOCAL_AUTHENTICATION_KEY = "LOCAL_AUTHENTICATION_KEY"

class PreferenceDataSource(private val dataSource: SharedPreferences) : DataSource {

    override suspend fun storeLocalAuthentication(localAuthentication: LocalAuthentication) {
        dataSource.edit().putString(
            LOCAL_AUTHENTICATION_KEY,
            Json.encodeToString(localAuthentication)
        ).apply()
    }

    override suspend fun getLocalAuthentication(): LocalAuthentication? {
        val localAuthentication =
            dataSource.getString(LOCAL_AUTHENTICATION_KEY, null) ?: return null
        return Json.decodeFromString<LocalAuthentication>(localAuthentication)
    }
}
