package com.iamnaran.firefly.data.repository.recipe

import androidx.room.withTransaction
import com.iamnaran.firefly.data.local.AppDatabase
import com.iamnaran.firefly.data.remote.service.RecipeApi
import com.iamnaran.firefly.di.qualifiers.DefaultDispatcher
import com.iamnaran.firefly.di.qualifiers.IoDispatcher
import com.iamnaran.firefly.utils.helper.networkBoundResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class RecipeRepositoryImpl  @Inject constructor(
    private val recipeApi: RecipeApi,
    private val appDatabase: AppDatabase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val externalScope: CoroutineScope,
): RecipeRepository {

    private val recipeDao = appDatabase.recipeDao()


    override suspend fun getRecipes() = networkBoundResource(
        query = {
            recipeDao.getAllRecipes()
        },
        fetch = {
            recipeApi.getRecipes()
        },
        saveFetchResult = { recipeResponse ->
            appDatabase.withTransaction {
                recipeDao.insertAllRecipes(recipeResponse.body()!!.recipes)
            }
        }
    )

    override suspend fun getRecipeById(recipeId: String) = networkBoundResource(
        query = {
            recipeDao.getRecipeById(recipeId.toInt())
        },
        fetch = {
            recipeApi.getRecipeById(recipeId)
        },
        saveFetchResult = { product ->
            appDatabase.withTransaction {
                recipeDao.insertRecipe(product.body()!!)
            }
        }

    )
}