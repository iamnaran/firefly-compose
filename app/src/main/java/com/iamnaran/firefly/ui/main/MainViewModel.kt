package com.iamnaran.firefly.ui.main

import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.domain.usecase.GetAccessTokenUseCase
import com.iamnaran.firefly.domain.usecase.GetLoginStatusUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLoginStatusUseCase: GetLoginStatusUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
    ) :
    BaseViewModel() {

    private val _loginStatus = MutableStateFlow(false)
    val loginStatus = _loginStatus.asStateFlow()

    private val _accessToken = MutableStateFlow("")
    val accessToken = _accessToken.asStateFlow()


    init {
        getLoggedInUserToken()
    }

    private fun getLoggedInUserToken() {
        viewModelScope.launch {
            getAccessTokenUseCase.invoke().onEach {
                _accessToken.value = it
            }.launchIn(this)
        }
    }

}