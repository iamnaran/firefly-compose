package com.iamnaran.firefly.ui.auth.login

import com.iamnaran.firefly.data.dto.User

sealed class LoginUIEvent {
    object Loading: LoginUIEvent()
    data class EmailState(val email:String): LoginUIEvent()
    data class PasswordState(val password:String): LoginUIEvent()
    data class NavigateToHome(val user: User) : LoginUIEvent()
    data class ShowErrorMessage(val message: String): LoginUIEvent()
}