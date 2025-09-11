package com.iamnaran.firefly.ui.auth.signup

import com.iamnaran.firefly.utils.common.ErrorState

data class SignUpState(
    val emailOrPhone: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isSignUpSuccessful: Boolean = false,
    val isLoading: Boolean = false,
    val signUpErrorState: SignUpErrorState = SignUpErrorState()

)


data class SignUpErrorState(
    val serverErrorState: ErrorState = ErrorState(),
    val invalidEmailOrPhoneErrorState: ErrorState = ErrorState(),
    val invalidPasswordErrorState: ErrorState = ErrorState(),
    val passwordMismatchErrorState: ErrorState = ErrorState(),
)