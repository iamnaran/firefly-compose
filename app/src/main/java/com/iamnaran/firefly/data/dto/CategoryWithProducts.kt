package com.iamnaran.firefly.data.dto

import com.iamnaran.firefly.data.local.entities.ProductEntity

data class CategoryWithProducts(
    val categoryName: String,
    val products: List<ProductEntity>
)