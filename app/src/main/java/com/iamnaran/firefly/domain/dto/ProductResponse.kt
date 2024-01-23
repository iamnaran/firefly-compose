package com.iamnaran.firefly.domain.dto

import com.google.gson.annotations.SerializedName
import com.iamnaran.firefly.data.local.entities.ProductEntity


data class ProductResponse(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val productEntities: List<ProductEntity>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)
