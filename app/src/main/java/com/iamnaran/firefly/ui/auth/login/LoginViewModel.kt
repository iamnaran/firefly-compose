package com.iamnaran.firefly.ui.auth.login

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.dto.UserResponse
import com.iamnaran.firefly.domain.usecase.auth.PostServerLoginUseCase
import com.iamnaran.firefly.domain.usecase.auth.SetLoggedInUserUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.ui.auth.core.GoogleAuthClient
import com.iamnaran.firefly.ui.auth.core.SignInResult
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
    private val googleAuthClient: GoogleAuthClient,
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
                doLoginWork()

            }

        }
    }

    private fun doLoginWork() {

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

    suspend fun signInIntentSender() = googleAuthClient.signIn()

    suspend fun onSignInResult(intent: Intent?) {
        val signInResult = googleAuthClient.signInWithIntent(intent!!)
        signInResult.run {
            if (signInResult.data != null) {
                val userResponse = UserResponse(
                    id = Math.random().toInt(),
                    username = signInResult.data.name,
                    email = signInResult.data.email,
                    firstName = signInResult.data.name,
                    lastName = "",
                    gender = "",
                    image = signInResult.data.profilePic.toString(),
                    token = signInResult.data.userId,
                )

                preferenceHelper.saveLoggedInUserDetails(
                    userResponse.id.toString(),
                    signInResult.data.userId,
                    true,
                )

                setLoggedInUserUseCase(userResponse)
                _loginState.value = _loginState.value.copy(
                    isLoginSuccessful = true,
                    isLoading = false
                )
            }

        }

    }


}