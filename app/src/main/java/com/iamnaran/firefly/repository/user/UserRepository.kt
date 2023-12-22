package com.iamnaran.firefly.repository.user

import com.iamnaran.firefly.domain.login.model.LoginRequest
import com.iamnaran.firefly.domain.login.model.LoginResponse

interface UserRepository {

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>
}