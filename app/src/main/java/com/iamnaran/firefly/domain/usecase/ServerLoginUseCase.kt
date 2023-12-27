package com.iamnaran.firefly.domain.usecase

import com.iamnaran.firefly.data.repository.auth.AuthRepository
import javax.inject.Inject

class ServerLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(username: String, password: String) =
        authRepository.postLogin(username, password)


}