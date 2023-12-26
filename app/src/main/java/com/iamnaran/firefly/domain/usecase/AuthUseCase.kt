package com.iamnaran.firefly.domain.usecase

import com.iamnaran.firefly.data.repository.auth.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun loginUseCase(username:String, password:String) = authRepository.postLogin(username, password)



}