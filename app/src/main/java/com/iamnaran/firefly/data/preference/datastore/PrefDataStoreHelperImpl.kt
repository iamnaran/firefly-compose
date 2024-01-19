package com.iamnaran.firefly.data.preference.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PrefDataStoreHelperImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PrefDataStoreHelper {
    override suspend fun saveLoggedInStatus() {
        dataStore.edit { preference ->
            preference[PrefKeys.LOGGED_IN_STATUS] = true
        }
    }

    override fun getLoggedInStatus(): Flow<Boolean> {
        return dataStore.data.map {
            preference -> preference[PrefKeys.LOGGED_IN_STATUS] ?: false
        }
    }

    override suspend fun isLoggedIn(): Boolean {
        return dataStore.data.map {
                preference -> preference[PrefKeys.LOGGED_IN_STATUS] ?: false
        }.first()
    }

    override fun getAccessToken(): Flow<String> {
        return dataStore.data.map { preference ->
                preference[PrefKeys.ACCESS_TOKEN] ?: ""
            }
    }

    override suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit { preference ->
            preference[PrefKeys.ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun clearPreference() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


}