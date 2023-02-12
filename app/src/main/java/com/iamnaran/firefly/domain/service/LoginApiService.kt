package com.iamnaran.firefly.domain.service

import com.iamnaran.firefly.domain.model.LoginRequest
import com.iamnaran.firefly.domain.model.LoginResponse
import retrofit2.Response
import retrofit2.http.POST

interface LoginApiService {
    @POST("login/vw")
    suspend fun serverLogin(
        loginRequest: LoginRequest
    ): Response<LoginResponse>
}