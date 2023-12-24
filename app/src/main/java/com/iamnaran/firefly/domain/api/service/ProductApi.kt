package com.iamnaran.firefly.domain.api.service

import com.iamnaran.firefly.domain.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun serverLogin(
        loginRequest: LoginRequest
    ): Response<User>

}