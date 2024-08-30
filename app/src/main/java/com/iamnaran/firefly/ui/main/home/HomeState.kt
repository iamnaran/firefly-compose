package com.iamnaran.firefly.ui.main.home

import androidx.paging.Pager
import com.iamnaran.firefly.data.local.entities.ProductEntity

data class HomeState(
    val allProductEntities: List<ProductEntity> = emptyList()
)