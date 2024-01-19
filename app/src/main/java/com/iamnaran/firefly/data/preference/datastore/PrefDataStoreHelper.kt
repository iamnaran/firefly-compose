package com.iamnaran.firefly.data.preference.datastore

import kotlinx.coroutines.flow.Flow

interface PrefDataStoreHelper {


    suspend fun saveLoggedInStatus()
    fun getLoggedInStatus(): Flow<Boolean>

    suspend fun isLoggedIn(): Boolean

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