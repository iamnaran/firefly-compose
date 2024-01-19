package com.iamnaran.firefly.data.repository.auth

import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.dto.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun postLogin(username: String,password: String): Flow<Resource<User>>

     fun doLogin(username: String,password: String): Flow<User>


}