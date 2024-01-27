package com.iamnaran.firefly.domain.usecase.auth

import com.iamnaran.firefly.data.repository.auth.AuthRepository
import javax.inject.Inject

class PostServerLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(username: String, password: String) =
        authRepository.postLogin(username, password)


}