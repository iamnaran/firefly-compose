package com.iamnaran.firefly.ui.auth.login

import com.iamnaran.firefly.utils.common.ErrorState

data class LoginState(
    val email: String = "kminchelle",
    val password: String = "0lelplR",
    val isLoginSuccessful: Boolean = false,
    val isLoading: Boolean = false,
    val loginErrorState: LoginErrorState = LoginErrorState()
)

data class LoginErrorState(
    val serverErrorState: ErrorState = ErrorState(),
    val invalidEmailErrorState: ErrorState = ErrorState(),
    val invalidPasswordErrorState: ErrorState = ErrorState(),
)