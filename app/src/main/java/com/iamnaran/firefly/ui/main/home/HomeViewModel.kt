package com.iamnaran.firefly.ui.main.home

import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.usecase.GetProductUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.ui.main.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
) : BaseViewModel() {


    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    private val _loginStatus = MutableStateFlow(false)
    val loginStatus: StateFlow<Boolean> = _loginStatus

    init {
        getProducts()
    }


    private fun getProducts() {

        viewModelScope.launch {
            getProductUseCase().collect { productResource ->
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