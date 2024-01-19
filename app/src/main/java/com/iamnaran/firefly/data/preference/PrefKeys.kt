package com.iamnaran.firefly.data.preference

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PrefKeys {

    const val PREF_FILE_NAME = "X_FIREFLY_X_"

    // Preference keys
    val ACCESS_TOKEN = stringPreferencesKey(PrefConstants.ACCESS_TOKEN)
    val LOGGED_IN_STATUS = booleanPreferencesKey(PrefConstants.LOGGED_IN_STATUS)



}
object PrefConstants{
    const val ACCESS_TOKEN = "ACCESS_TOKEN"
    const val LOGGED_IN_STATUS = "LOGGED_IN_STATUS"
}