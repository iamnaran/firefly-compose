package com.iamnaran.firefly.domain.usecase.recipe

import com.iamnaran.firefly.data.repository.recipe.RecipeRepository
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(recipeId: String) =
        recipeRepository.getRecipeById(recipeId)


}