package com.iamnaran.firefly.data.preference.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PrefKeys {

    const val PREF_FILE_NAME = "X_FIREFLY_X_DATA_STORE"

    // Preference keys
    val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
    val LOGGED_IN_STATUS = booleanPreferencesKey("LOGGED_IN_STATUS")


}