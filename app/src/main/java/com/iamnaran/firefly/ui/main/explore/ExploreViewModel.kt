package com.iamnaran.firefly.ui.main.explore

import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.usecase.product.GetProductUseCase
import com.iamnaran.firefly.domain.usecase.product.GetProductsByCategoriesUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.ui.main.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val getProductsByCategoriesUseCase: GetProductsByCategoriesUseCase,
) : BaseViewModel() {


    private val _exploreState = MutableStateFlow(ExploreState())
    val exploreState: StateFlow<ExploreState> = _exploreState

    init {
        getProducts()
    }


    private fun getProducts() {

        viewModelScope.launch {
            getProductUseCase().collectLatest { productResource ->

                when (productResource) {
                    is Resource.Success -> {
                        val categoriesWithProducts =
                            getProductsByCategoriesUseCase(productResource.data!!)
                        _exploreState.value = ExploreState(categoriesWithProducts)
                    }
                    else -> {
                    }
                }
            }

        }
    }

}