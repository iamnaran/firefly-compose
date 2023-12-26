package com.iamnaran.firefly.data.entities

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user")
@Immutable
data class Product(
    @PrimaryKey val id: Long,
    val fullName: String,
    val email: String,
    val contact: String,
    val profileImage: String
)
