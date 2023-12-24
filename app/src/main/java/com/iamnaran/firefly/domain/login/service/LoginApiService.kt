package com.iamnaran.firefly.domain.login.service

import com.iamnaran.firefly.domain.login.model.LoginRequest
import com.iamnaran.firefly.domain.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.POST

interface LoginApiService {
    @POST("login/vw")
    suspend fun serverLogin(
        loginRequest: LoginRequest
    ): Response<LoginResponse>

}