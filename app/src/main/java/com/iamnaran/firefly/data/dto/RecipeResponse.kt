package com.iamnaran.firefly.data.dto

import com.iamnaran.firefly.data.local.entities.RecipeEntity


data class RecipeResponse(
    val recipes: List<RecipeEntity>,
    val total: Long,
    val skip: Long,
    val limit: Long,
)