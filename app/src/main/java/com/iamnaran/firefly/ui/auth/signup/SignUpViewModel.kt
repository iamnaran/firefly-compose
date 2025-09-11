package com.iamnaran.firefly.ui.auth.signup

import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.usecase.auth.signup.SignUpUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) :
    BaseViewModel() {

    private val TAG = AppLog.tagFor(this.javaClass)

    private val _signUpState = MutableStateFlow(SignUpState())
    val singUpState = _signUpState.asStateFlow()


    fun handleSignUpUIEvent(event: SignUpUIEvent) {
        when (event) {
            is SignUpUIEvent.ConfirmPasswordChanged -> {
                updateState{
                    copy(confirmPassword = event.inputConfirmPasswordValue)
                }
            }
            is SignUpUIEvent.EmailOrPhoneChanged -> {
                updateState {
                    copy(emailOrPhone = event.inputEmailOrPhoneValue)
                }

            }
            SignUpUIEvent.OnSubmit -> {
                proceedSignUp()

            }
            is SignUpUIEvent.PasswordChanged -> {

                updateState {
                    copy(password = event.inputPasswordValue)
                }

            }
        }
    }

    private fun updateState(update: SignUpState.() -> SignUpState) {
        _signUpState.value = _signUpState.value.update()
    }

    fun proceedSignUp() {

        viewModelScope.launch {

            signUpUseCase.invoke(_signUpState.value.emailOrPhone,_signUpState.value.password).collect {
                    resource ->
                when(resource){
                    is Resource.Success -> {


                    }

                    else -> {

                    }
                }
            }

        }
    }


}