package com.iamnaran.firefly.data.preference

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : PreferenceHelper {

    override suspend fun saveLoggedInUserId(userId: String) {
        val preference = sharedPreferences.edit();
        preference.putString(PrefConstants.PREF_USER_TOKEN_KEY, userId)
        preference.apply();
    }

    override suspend fun getLoggedInUserId(): String? {
        return sharedPreferences.getString(PrefConstants.PREF_USER_TOKEN_KEY, null)
    }


}