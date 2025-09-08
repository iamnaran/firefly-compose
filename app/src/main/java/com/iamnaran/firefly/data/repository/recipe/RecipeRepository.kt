package com.iamnaran.firefly.data.repository.recipe

import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.local.entities.RecipeEntity
import com.iamnaran.firefly.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipes(): Flow<Resource<List<RecipeEntity>>>
    suspend fun getRecipeById(recipeId: String): Flow<Resource<RecipeEntity>>

}