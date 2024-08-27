package com.iamnaran.firefly.domain.usecase.auth

import com.iamnaran.firefly.data.repository.auth.AuthRepository
import com.iamnaran.firefly.data.dto.UserResponse
import com.iamnaran.firefly.data.dto.toUserEntity
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userId: String) =
        authRepository.getUserById(userId.toLong())


}