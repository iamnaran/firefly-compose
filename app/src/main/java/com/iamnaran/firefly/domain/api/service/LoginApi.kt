package com.iamnaran.firefly.domain.api.service

import com.iamnaran.firefly.domain.model.User
import retrofit2.http.Field
import retrofit2.http.GET

interface LoginApi {
    @GET("auth/login")
    suspend fun serverLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): User

}