package com.iamnaran.firefly.domain.dto

import com.iamnaran.firefly.data.local.entities.RecipeEntity


data class RecipeResponse(
    val recipes: List<RecipeEntity>,
    val total: Long,
    val skip: Long,
    val limit: Long,
)