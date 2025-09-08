package com.iamnaran.firefly.domain.usecase.auth

import com.iamnaran.firefly.data.repository.auth.AuthRepository
import com.iamnaran.firefly.data.dto.UserResponse
import com.iamnaran.firefly.data.dto.toUserEntity
import javax.inject.Inject

class SetLoggedInUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userResponse: UserResponse) =
        authRepository.storeLoggedInUser(userResponse.toUserEntity())


}