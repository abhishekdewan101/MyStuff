package com.adewan.mystuff.datastore

import android.content.SharedPreferences
import com.adewan.mystuff.models.LocalAuthentication
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface LocalDataStore {
    fun storeLocalAuthentication(localAuthentication: LocalAuthentication)
    fun getLocalAuthentication(): LocalAuthentication?
    fun onBoardingCompleted()
    fun getOnBoardingCompleted(): Boolean
}

private const val LOCAL_AUTHENTICATION_KEY = "LOCAL_AUTHENTICATION_KEY"
private const val ONBOARDING_KEY = "ONBOARDING_KEY"

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

    override fun onBoardingCompleted() {
        dataSource.edit().putBoolean(ONBOARDING_KEY, true).apply()
    }

    override fun getOnBoardingCompleted(): Boolean {
        return dataSource.getBoolean(ONBOARDING_KEY, false)
    }
}
