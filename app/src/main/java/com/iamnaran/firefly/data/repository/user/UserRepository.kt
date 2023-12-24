package com.iamnaran.firefly.data.repository.user

import com.iamnaran.firefly.domain.model.User
import retrofit2.Response

interface UserRepository {

    suspend fun login(loginRequest: LoginRequest): Response<User>

}