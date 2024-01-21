package com.iamnaran.firefly.ui.main.home

import com.iamnaran.firefly.data.local.entities.Product
import com.iamnaran.firefly.domain.dto.User

data class HomeState(
    val allProducts: List<Product> = emptyList()
)