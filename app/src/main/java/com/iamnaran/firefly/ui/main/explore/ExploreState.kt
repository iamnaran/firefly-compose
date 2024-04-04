package com.iamnaran.firefly.ui.main.explore

import com.iamnaran.firefly.data.local.entities.ProductEntity

data class ExploreState(
    val allProductEntities: List<ProductEntity> = emptyList()
)