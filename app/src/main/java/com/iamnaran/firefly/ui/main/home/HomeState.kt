package com.iamnaran.firefly.ui.main.home

import com.iamnaran.firefly.data.local.entities.ProductEntity

data class HomeState(
    val allProductEntities: List<ProductEntity> = emptyList()
)