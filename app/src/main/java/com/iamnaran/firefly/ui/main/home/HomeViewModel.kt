package com.iamnaran.firefly.ui.main.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.usecase.product.GetProductPaginatedUseCase
import com.iamnaran.firefly.domain.usecase.product.GetProductUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val getProductPaginatedUseCase: GetProductPaginatedUseCase
) : BaseViewModel() {


    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    private val _loginStatus = MutableStateFlow(false)
    val loginStatus: StateFlow<Boolean> = _loginStatus

    init {
        getProducts()
    }

    val paginatedProducts: Flow<PagingData<ProductEntity>> = getProductPaginatedUseCase()
        .cachedIn(viewModelScope)


    private fun getProducts() {

        viewModelScope.launch {
            getProductUseCase().collectLatest { productResource ->

                AppLog.showLog("Called "+productResource.message)
                AppLog.showLog("Called "+ (productResource.data?.size ?: 100))
                when (productResource) {
                    is Resource.Loading ->{
                        _homeState.value = HomeState(productResource.data!!)
                    }
                    is Resource.Success -> {
                        _homeState.value = HomeState(productResource.data!!)
                    }
                    else -> {
                    }
                }
            }
        }
    }

}