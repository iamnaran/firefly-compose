package com.iamnaran.firefly.data.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceHelper {


    suspend fun saveLoggedInStatus(status: Boolean)
    fun  getLoggedInStatus(): Flow<Boolean>


    /**
     * returns user name flow
     * */
    fun getAccessToken(): Flow<String>

    /**
     * saves user name in data store
     * */
    suspend fun saveAccessToken(accessToken: String)

    suspend fun clearPreference()
}