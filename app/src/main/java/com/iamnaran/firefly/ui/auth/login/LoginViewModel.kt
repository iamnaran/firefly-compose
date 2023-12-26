package com.iamnaran.firefly.ui.auth.login

import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.data.api.Resource
import com.iamnaran.firefly.domain.usecase.AuthUseCase
import com.iamnaran.firefly.ui.common.BaseViewModel
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase) :
    BaseViewModel() {

    private val TAG = AppLog.tagFor(this.javaClass)

    private val _emailState = MutableStateFlow("kminchelle")
    val emailState: StateFlow<String> = _emailState

    private val _passwordState = MutableStateFlow("0lelplR")
    val passwordState: StateFlow<String> = _passwordState

    fun setEmail(email: String) {
        _emailState.value = email
    }

    fun setPassword(password: String) {
        _passwordState.value = password
    }

    // State for tracking login status
    private val _loginState = MutableStateFlow<LoginUIEvent>(LoginUIEvent.Loading)
    val loginState: StateFlow<LoginUIEvent> = _loginState


    fun login() {
        viewModelScope.launch {
            authUseCase.loginUseCase(_emailState.value, _passwordState.value).onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _loginState.value = LoginUIEvent.Loading
                    }

                    is Resource.Success -> {
                        _loginState.value = LoginUIEvent.NavigateToHome(resource.data!!)
                    }

                    else -> {
                        _loginState.value =
                            LoginUIEvent.ShowErrorMessage(resource.message.toString())

                    }
                }
            }.launchIn(this)
        }
    }

}