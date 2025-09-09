package com.iamnaran.firefly.ui.auth.login

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.data.dto.UserResponse
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.data.remote.Resource
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


    fun handleLoginUIEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> updateState { copy(email = event.inputEmailValue) }
            is LoginUIEvent.PasswordChanged -> updateState { copy(password = event.inputPasswordValue) }
            is LoginUIEvent.OnSubmit -> {
                // Changed: combine multiple updates into one to avoid multiple recompositions
                updateState {
                    copy(
                        isLoading = true,
                        isLoginSuccessful = false,
                        loginErrorState = LoginErrorState(serverErrorState = ErrorState(false, ""))
                    )
                }
                doLoginWork()
            }
        }
    }
    private fun doLoginWork() {
        viewModelScope.launch {
            postServerLoginUseCase(_loginState.value.email, _loginState.value.password)
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            resource.data?.let { user ->
                                saveUserData(user.id.toString(), user.token, user)
                                updateState { copy(isLoginSuccessful = true, isLoading = false) } // Changed: combine updates
                            } ?: updateState { copy(isLoading = false) } // Edge case: null user
                        }

                        is Resource.Loading -> updateState { copy(isLoading = true) } // Changed: only update isLoading

                        is Resource.Error -> updateState {
                            // Changed: single update for error handling
                            copy(
                                isLoading = false,
                                isLoginSuccessful = false,
                                loginErrorState = LoginErrorState(
                                    serverErrorState = ErrorState(true, resource.message.orEmpty())
                                )
                            )
                        }

                        else -> {
                            updateState { copy(isLoading = false) }
                        }
                    }
                }
        }
    }

    private suspend fun saveUserData(userId: String, token: String, user: UserResponse) {
        preferenceHelper.saveLoggedInUserDetails(userId, token, true)
        setLoggedInUserUseCase(user)
    }


    private var _googleSignInLauncher: ActivityResultLauncher<IntentSenderRequest>? = null
    fun setGoogleSignInLauncher(launcher: ActivityResultLauncher<IntentSenderRequest>) {
        _googleSignInLauncher = launcher
    }

    private fun updateState(update: LoginState.() -> LoginState) {
        _loginState.value = _loginState.value.update()
    }



    fun signInWithGoogle() {
        viewModelScope.launch {
            val intentSender = googleAuthClient.signIn() ?: return@launch
            // Launch Google Sign-In through stored launcher
            _googleSignInLauncher?.launch(IntentSenderRequest.Builder(intentSender).build())
        }
    }

    // Handle Google Sign-In result
    suspend fun handleGoogleSignInResult(intent: Intent?) {
        val result: SignInResult = googleAuthClient.signInWithIntent(intent ?: return)
        result.data?.let { data ->
            val user = UserResponse(
                id = (System.currentTimeMillis() % Int.MAX_VALUE).toInt(), // Changed: generate unique ID
                username = data.name,
                email = data.email,
                firstName = data.name,
                lastName = "",
                gender = "",
                image = data.profilePic.toString(),
                token = data.userId
            )
            saveUserData(user.id.toString(), data.userId, user)
            updateState { copy(isLoginSuccessful = true, isLoading = false) } // Changed: single state update
        }
    }


}