package com.iamnaran.firefly.ui.main.home.productdetail

import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.domain.usecase.GetProductUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
) : BaseViewModel() {


    private val _productState = MutableStateFlow(ProductState())
    val productState = _productState.asStateFlow()

    private val _loginStatus = MutableStateFlow(false)
    val loginStatus: StateFlow<Boolean> = _loginStatus

    init {
        getProducts()
    }


    private fun getProducts() {

        viewModelScope.launch {


        }
    }

}