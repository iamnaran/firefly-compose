package com.iamnaran.firefly.data.local.entities

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("user")
@Immutable
data class UserEntity(
    @ColumnInfo(name = "id") @SerializedName("id")
    @PrimaryKey val id: Long,

    @ColumnInfo(name = "fullName")
    @SerializedName("fullName")
    val fullName: String,

    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String,

    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    val gender: String,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String,

    @ColumnInfo(name = "username")
    @SerializedName("username")
    val username: String,

    )
