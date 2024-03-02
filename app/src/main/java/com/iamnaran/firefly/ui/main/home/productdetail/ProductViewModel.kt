package com.iamnaran.firefly.ui.main.home.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.usecase.product.GetProductByIdUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productByIdUseCase: GetProductByIdUseCase,
) : BaseViewModel() {

    private val productId: String = checkNotNull(savedStateHandle["productId"])

    private val _productState = MutableStateFlow(ProductState(null))
    val productState = _productState.asStateFlow()


    init {
        getProductById(productId)
    }


    private fun getProductById(productId: String) {

        viewModelScope.launch {
            productByIdUseCase(productId).collectLatest { productResource ->

                when (productResource) {
                    is Resource.Loading -> {
                        _productState.value = ProductState(productResource.data!!)
                    }

                    is Resource.Success -> {
                        _productState.value = ProductState(productResource.data!!)
                    }

                    else -> {

                    }
                }

            }

        }
    }

}