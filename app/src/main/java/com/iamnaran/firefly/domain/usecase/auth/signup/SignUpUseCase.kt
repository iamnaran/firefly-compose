package com.iamnaran.firefly.domain.usecase.auth.signup

import com.iamnaran.firefly.data.repository.auth.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String) =
        authRepository.postRegister(username, password)
}
