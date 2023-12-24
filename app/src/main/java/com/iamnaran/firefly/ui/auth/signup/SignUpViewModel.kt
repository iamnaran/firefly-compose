package com.iamnaran.firefly.ui.auth.signup

import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.data.repository.user.UserRepository
import com.iamnaran.firefly.ui.common.BaseViewModel
import com.iamnaran.firefly.ui.common.ViewState
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val TAG = AppLog.tagFor(this.javaClass)

    private val _emailState = MutableStateFlow("")
    val emailState: StateFlow<String> = _emailState

    private val _passwordState = MutableStateFlow("")
    val passwordState: StateFlow<String> = _passwordState

    fun setEmail(email: String) {
        _emailState.value = email
    }
    fun setPassword(password: String) {
        _passwordState.value = password
    }

    // State for tracking login status
    private val _signUpState = MutableStateFlow<ViewState<Nothing>>(ViewState.Initial)
    val signUpState: StateFlow<ViewState<Nothing>> = _signUpState


    fun signUp() {

        viewModelScope.launch {
            _signUpState.value = ViewState.Loading
//            userRepository.login(loginRequest).collect { response ->
//                when (response) {
//                    is Result.Success -> _loginStateResponse.value = response.data
//
//                    is Result.GenericError -> { //Error
//                    }
//                    else -> { //Loading
//                    }
//                }
//            }
        }
    }

}