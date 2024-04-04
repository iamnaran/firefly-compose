package com.iamnaran.firefly.domain.dto

import com.iamnaran.firefly.data.local.entities.ProductEntity

data class CategoryWithProducts(
    val categoryName: String,
    val products: List<ProductEntity>
)