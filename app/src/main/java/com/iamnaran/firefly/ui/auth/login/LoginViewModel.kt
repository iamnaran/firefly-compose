package com.iamnaran.firefly.ui.auth.login

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.usecase.auth.PostServerLoginUseCase
import com.iamnaran.firefly.domain.usecase.auth.SetLoggedInUserUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postServerLoginUseCase: PostServerLoginUseCase,
    private val setLoggedInUserUseCase: SetLoggedInUserUseCase,
    private val preferenceHelper: PreferenceHelper,
    private val gson: Gson
) :
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
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Loading)
    val loginState: StateFlow<LoginState> = _loginState


    fun login() {
        viewModelScope.launch {
            postServerLoginUseCase(_emailState.value, _passwordState.value).onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _loginState.value = LoginState.Loading
                    }

                    is Resource.Success -> {
                        if (resource.data != null) {
                            preferenceHelper.saveLoggedInUserDetails(
                                resource.data.id.toString(),
                                resource.data.token,
                                true
                            )
                            setLoggedInUserUseCase(resource.data)
                            _loginState.value = LoginState.NavigateToHome(resource.data)
                        }
                    }

                    else -> {
                        _loginState.value =
                            LoginState.ShowErrorMessage(resource.message.toString())

                    }
                }
            }.launchIn(this)
        }
    }

}