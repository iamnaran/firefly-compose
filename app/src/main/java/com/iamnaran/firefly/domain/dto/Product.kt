package com.iamnaran.firefly.domain.dto

import com.google.gson.annotations.SerializedName
import com.iamnaran.firefly.data.local.entities.Product


data class AllProduct(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)
