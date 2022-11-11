package com.adewan.mystuff.datastore

import android.content.SharedPreferences
import com.adewan.mystuff.models.LocalAuthentication
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface LocalDataStore {
    fun storeLocalAuthentication(localAuthentication: LocalAuthentication)
    fun getLocalAuthentication(): LocalAuthentication?
}

private const val LOCAL_AUTHENTICATION_KEY = "LOCAL_AUTHENTICATION_KEY"

class PreferenceDataStore(private val dataSource: SharedPreferences) : LocalDataStore {

    override fun storeLocalAuthentication(localAuthentication: LocalAuthentication) {
        dataSource.edit().putString(
            LOCAL_AUTHENTICATION_KEY,
            Json.encodeToString(localAuthentication)
        ).apply()
    }

    override fun getLocalAuthentication(): LocalAuthentication? {
        val localAuthentication =
            dataSource.getString(LOCAL_AUTHENTICATION_KEY, null) ?: return null
        return Json.decodeFromString<LocalAuthentication>(localAuthentication)
    }
}
