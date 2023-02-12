package com.iamnaran.firefly.domain.login.usecase

import com.iamnaran.firefly.domain.login.model.LoginRequest
import com.iamnaran.firefly.domain.login.model.LoginResponse
import com.iamnaran.firefly.repository.user.UserRepository
import javax.inject.Inject

class ServerLoginUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getNewsDetail(loginRequest: LoginRequest): Result<LoginResponse> {
        return userRepository.login(loginRequest)
    }
}