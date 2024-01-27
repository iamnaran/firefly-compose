package com.iamnaran.firefly.data.preference

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences) : PreferenceHelper {

    override suspend fun saveLoggedInUserId(userId: String) {
        val preference = sharedPreferences.edit();
        preference.putString(PrefConstants.PREF_USER_ID, userId)
        preference.apply();
    }

    override fun getLoggedInUserId(): String? {
        return sharedPreferences.getString(PrefConstants.PREF_USER_ID, null)
    }


    override suspend fun saveAccessToken(accessToken: String) {
        val preference = sharedPreferences.edit();
        preference.putString(PrefConstants.PREF_USER_TOKEN, accessToken)
        preference.apply();
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(PrefConstants.PREF_USER_TOKEN, null)
    }

    override suspend fun saveLoggedInStatus(status: Boolean) {
        val preference = sharedPreferences.edit();
        preference.putBoolean(PrefConstants.PREF_LOGGED_IN_STATUS, status)
        preference.apply();
    }

    override fun getLoggedInStatus(): Boolean {
        return sharedPreferences.getBoolean(PrefConstants.PREF_LOGGED_IN_STATUS, false)

    }

    override suspend fun saveLoggedInUserDetails(
        userId: String,
        accessToken: String,
        loginStatus: Boolean
    ) {

        saveLoggedInUserId(userId)
        saveAccessToken(accessToken)
        saveLoggedInStatus(loginStatus)
    }

    override suspend fun clearPreference() {
        val preference = sharedPreferences.edit();
        preference.clear().apply()
    }


}