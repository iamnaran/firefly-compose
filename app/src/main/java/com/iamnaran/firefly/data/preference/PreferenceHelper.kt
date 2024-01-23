package com.iamnaran.firefly.data.preference

interface PreferenceHelper {

    suspend fun saveLoggedInUserId(userId: String)
    fun getLoggedInUserId(): String?

    suspend fun saveAccessToken(accessToken: String)
    fun getAccessToken(): String?

    suspend fun saveLoggedInStatus(status: Boolean)
    fun getLoggedInStatus(): Boolean

    suspend fun saveLoggedInUserDetails(userId: String, accessToken: String, loginStatus: Boolean)
}