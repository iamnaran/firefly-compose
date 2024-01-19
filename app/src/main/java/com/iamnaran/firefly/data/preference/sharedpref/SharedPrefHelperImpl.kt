package com.iamnaran.firefly.data.preference.sharedpref

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class SharedPrefHelperImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : SharedPrefHelper {

    override suspend fun saveLoggedInUserId(userId: String) {
        val preference = sharedPreferences.edit();
        preference.putString(PrefConstants.USER_ID_PREF_KEY, userId)
        preference.apply();
    }

    override fun getLoggedInUserId(): String? {
        return sharedPreferences.getString(PrefConstants.USER_ID_PREF_KEY, null)
    }


    override suspend fun saveAccessToken(accessToken: String) {
        val preference = sharedPreferences.edit();
        preference.putString(PrefConstants.USER_TOKEN_PREF_KEY, accessToken)
        preference.apply();
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(PrefConstants.USER_TOKEN_PREF_KEY, null)
    }

    override suspend fun saveLoggedInStatus(status: Boolean) {
        val preference = sharedPreferences.edit();
        preference.putBoolean(PrefConstants.LOGGED_IN_STATUS, status)
        preference.apply();
    }

    override fun getLoggedInStatus(): Boolean {
        return sharedPreferences.getBoolean(PrefConstants.LOGGED_IN_STATUS, false)

    }


}