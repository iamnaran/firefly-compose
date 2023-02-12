package com.iamnaran.firefly.data.preference

interface PreferenceHelper {

    suspend fun saveLoggedInUserId(userId: String)

    suspend fun getLoggedInUserId(): String?
}