package com.iamnaran.firefly.data.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user")
@Immutable
data class User(
    @PrimaryKey val id: Long,
    val fullName: String,
    val email: String,
    val contact: String,
    val profileImage: String
)
