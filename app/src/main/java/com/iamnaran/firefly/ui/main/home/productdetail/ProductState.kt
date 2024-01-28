package com.iamnaran.firefly.ui.main.home.productdetail

import com.iamnaran.firefly.data.local.entities.ProductEntity

data class ProductState(
    val allProductEntities: List<ProductEntity> = emptyList()
)