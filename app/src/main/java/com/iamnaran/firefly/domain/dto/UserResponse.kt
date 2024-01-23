package com.iamnaran.firefly.domain.dto

import com.google.gson.annotations.SerializedName
import com.iamnaran.firefly.data.local.entities.UserEntity

data class UserResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val image: String,
    @SerializedName("token") val token: String,
)

fun UserResponse.toUserEntity() = UserEntity(
    id = id.toLong(),
    fullName = "$firstName $lastName",
    email = email,
    gender = gender,
    image = image,
    username = username
);