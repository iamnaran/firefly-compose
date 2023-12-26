package com.iamnaran.firefly.data.repository.auth

import com.iamnaran.firefly.data.api.Resource
import com.iamnaran.firefly.data.dto.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun postLogin(username: String,password: String): Flow<Resource<User>>

}