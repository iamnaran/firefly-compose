package com.iamnaran.firefly.ui.main.notification.recipe.recipedetail

import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.usecase.product.GetProductByIdUseCase
import com.iamnaran.firefly.domain.usecase.recipe.GetRecipeByIdUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeByIdUseCase: GetRecipeByIdUseCase,
) : BaseViewModel() {

    private val _recipeState = MutableStateFlow(RecipeState(null))
    val productState = _recipeState.asStateFlow()

    public fun getRecipeById(recipeId: String) {

        viewModelScope.launch {
            recipeByIdUseCase(recipeId).collectLatest { recipeResource ->

                when (recipeResource) {
                    is Resource.Loading -> {
                        _recipeState.value = RecipeState(recipeResource.data!!)
                    }

                    is Resource.Success -> {
                        _recipeState.value = RecipeState(recipeResource.data!!)
                    }

                    else -> {

                    }
                }

            }

        }
    }

}