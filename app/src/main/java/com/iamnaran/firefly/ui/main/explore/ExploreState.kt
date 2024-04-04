package com.iamnaran.firefly.ui.main.explore

import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.domain.dto.CategoryWithProducts

data class ExploreState(
    val allProductEntities: List<CategoryWithProducts> = emptyList()
)