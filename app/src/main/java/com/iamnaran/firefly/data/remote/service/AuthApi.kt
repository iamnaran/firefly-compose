package com.iamnaran.firefly.data.remote.service

import com.iamnaran.firefly.data.dto.UserResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("auth/login")
    fun authApi(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flow<UserResponse>

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun registerApi(
        @Field("username") username: String,
        @Field("password") password: String
    ): retrofit2.Response<UserResponse>

}