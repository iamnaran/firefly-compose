package com.iamnaran.firefly.domain.usecase

import com.iamnaran.firefly.domain.model.User
import com.iamnaran.firefly.data.repository.user.UserRepository
import retrofit2.Response
import javax.inject.Inject

class ServerLoginUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getNewsDetail(loginRequest: LoginRequest): Response<User> {
        return userRepository.login(loginRequest)
    }
}