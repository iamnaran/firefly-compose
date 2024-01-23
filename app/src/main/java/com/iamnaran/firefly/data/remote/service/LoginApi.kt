package com.iamnaran.firefly.data.remote.service

import com.iamnaran.firefly.domain.dto.UserResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun serverLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<UserResponse>

}