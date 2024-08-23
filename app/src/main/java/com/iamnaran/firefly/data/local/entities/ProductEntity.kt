package com.iamnaran.firefly.data.local.entities

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("product")
@Immutable
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo("id")
    @SerializedName("id")
    val id: Long,

    @ColumnInfo("title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo("description")
    @SerializedName("description")
    val description: String,

    @ColumnInfo("category")
    @SerializedName("category")
    val category: String,

    @ColumnInfo("price")
    @SerializedName("price")
    val price: Float,

    @ColumnInfo("rating")
    @SerializedName("rating")
    val rating: String,

    @ColumnInfo("stock")
    @SerializedName("stock")
    val stock: Int,

    @ColumnInfo("thumbnail")
    @SerializedName("thumbnail")
    val thumbnail: String,
)
