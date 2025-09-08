package com.iamnaran.firefly.ui.main.notification

import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.local.entities.RecipeEntity

data class NotificationState(
    val allRecipeList: List<RecipeEntity> = emptyList()
)