package com.iamnaran.firefly.data.repository.auth

import com.iamnaran.firefly.data.local.entities.UserEntity
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.data.dto.UserResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

     fun postLogin(username: String,password: String): Flow<Resource<UserResponse>>

     fun doLogin(username: String,password: String): Flow<UserResponse>

     suspend fun storeLoggedInUser(user: UserEntity)

     suspend fun getUserById(userId: Long): Flow<UserEntity>

     suspend fun getAllUsers(): Flow<List<UserEntity>>


}