package com.iamnaran.firefly.data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferenceHelper {
    override suspend fun saveLoggedInStatus(status: Boolean) {
        dataStore.edit { preference ->
            preference[PrefKeys.LOGGED_IN_STATUS] = status
        }
    }

    override fun getLoggedInStatus(): Flow<Boolean> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preference ->
                preference[PrefKeys.LOGGED_IN_STATUS] != null
            }
    }

    override fun getAccessToken(): Flow<String> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preference ->
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