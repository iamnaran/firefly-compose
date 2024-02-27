package com.iamnaran.firefly.ui.auth.login

import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.usecase.auth.PostServerLoginUseCase
import com.iamnaran.firefly.domain.usecase.auth.SetLoggedInUserUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.utils.AppLog
import com.iamnaran.firefly.utils.common.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postServerLoginUseCase: PostServerLoginUseCase,
    private val setLoggedInUserUseCase: SetLoggedInUserUseCase,
    private val preferenceHelper: PreferenceHelper,
) :
    BaseViewModel() {

    private val TAG = AppLog.tagFor(this.javaClass)

    // State for tracking login status
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()


    fun handleLoginUIEvent(loginUiEvents: LoginUIEvent) {

        when (loginUiEvents) {

            is LoginUIEvent.EmailChanged -> {
                _loginState.value = loginState.value.copy(
                    password = loginUiEvents.inputEmailValue
                )
            }

            is LoginUIEvent.PasswordChanged -> {

                _loginState.value = _loginState.value.copy(
                    password = loginUiEvents.inputPasswordValue
                )

            }

            is LoginUIEvent.OnSubmit -> {
                _loginState.value = _loginState.value.copy(
                    isLoading = true,
                    isLoginSuccessful = false,
                )
                _loginState.value = _loginState.value.copy(
                    loginErrorState = LoginErrorState(
                        serverErrorState = ErrorState(
                            false,
                            serverErrorMsg = ""
                        )
                    )
                )


            }

        }

    }


    fun doPostLoginWork(){
        viewModelScope.launch {
            postServerLoginUseCase(
                _loginState.value.email,
                _loginState.value.password
            )
                .onStart {

                }
                .onCompletion {

                }
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            if (resource.data != null) {
                                AppLog.showLog("----------LOGIN SUCCESS----------")
                                preferenceHelper.saveLoggedInUserDetails(
                                    resource.data.id.toString(),
                                    resource.data.token,
                                    true,
                                )
                                setLoggedInUserUseCase(resource.data)
                                _loginState.value = _loginState.value.copy(
                                    isLoginSuccessful = true,
                                    isLoading = false
                                )

                            }
                        }

                        is Resource.Loading -> {
                            _loginState.value = _loginState.value.copy(
                                isLoading = true
                            )
                        }

                        else -> {
                            _loginState.value = _loginState.value.copy(
                                isLoginSuccessful = false,
                                isLoading = false
                            )

                            _loginState.value = _loginState.value.copy(
                                loginErrorState = LoginErrorState(
                                    serverErrorState = ErrorState(
                                        true,
                                        serverErrorMsg = resource.message.toString()
                                    )
                                )
                            )
                        }
                    }
                }
        }

    }

}