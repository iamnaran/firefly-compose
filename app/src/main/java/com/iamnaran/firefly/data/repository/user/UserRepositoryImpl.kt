package com.iamnaran.firefly.data.repository.user

import com.iamnaran.firefly.domain.model.User
import com.iamnaran.firefly.domain.api.service.LoginApi
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi
) : UserRepository {

    override suspend fun login(loginRequest: LoginRequest): Response<User> {
        return loginApi.serverLogin(loginRequest);
    }


}