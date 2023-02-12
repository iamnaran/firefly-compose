package com.iamnaran.firefly.repository.user

import com.iamnaran.firefly.domain.model.LoginRequest
import com.iamnaran.firefly.domain.model.LoginResponse
import com.iamnaran.firefly.domain.service.LoginApiService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val loginApiService: LoginApiService
) : UserRepository {

    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        TODO("Not yet implemented")
//        loginApiService.serverLogin(loginRequest);

    }


}