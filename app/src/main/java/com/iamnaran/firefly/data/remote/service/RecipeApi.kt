package com.iamnaran.firefly.data.remote.service

import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.local.entities.RecipeEntity
import com.iamnaran.firefly.domain.dto.ProductResponse
import com.iamnaran.firefly.domain.dto.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApi {
    @GET("recipes")
    suspend fun getRecipes(): Response<RecipeResponse>

    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") recipeId: String): Response<RecipeEntity>


}