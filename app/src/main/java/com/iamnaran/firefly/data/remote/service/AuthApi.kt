package com.iamnaran.firefly.data.remote.service

import com.iamnaran.firefly.domain.dto.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("auth/login")
    fun authApi(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flow<User>

}