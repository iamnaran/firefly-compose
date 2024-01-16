package com.iamnaran.firefly.ui.main

import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.domain.usecase.GetLoginStatusUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getLoginStatusUseCase: GetLoginStatusUseCase) :
    BaseViewModel() {

    private val _loginStatus = MutableStateFlow(false)
    val loginStatus: StateFlow<Boolean> = _loginStatus

    init {
        getLoggedInStatus()
    }

    private fun getLoggedInStatus() {
        viewModelScope.launch {
            getLoginStatusUseCase().onEach {
                _loginStatus.value = it;
            }.launchIn(this)
        }
    }

}