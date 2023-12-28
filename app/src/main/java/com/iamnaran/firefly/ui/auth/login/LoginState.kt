package com.iamnaran.firefly.ui.auth.login

import com.iamnaran.firefly.domain.dto.User

sealed class LoginState {
    object Loading: LoginState()
    data class EmailState(val email:String): LoginState()
    data class PasswordState(val password:String): LoginState()
    data class NavigateToHome(val user: User) : LoginState()
    data class ShowErrorMessage(val message: String): LoginState()
}