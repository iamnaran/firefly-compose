package com.iamnaran.firefly.data.preference.sharedpref

interface SharedPrefHelper {

    suspend fun saveLoggedInUserId(userId: String)
    fun getLoggedInUserId(): String?

    suspend fun saveAccessToken(accessToken: String)
    fun getAccessToken(): String?


    suspend fun saveLoggedInStatus(status: Boolean)
    fun getLoggedInStatus(): Boolean
}