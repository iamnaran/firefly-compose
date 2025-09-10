package com.iamnaran.firefly.ui.auth.signup

sealed class SignUpUIEvent {
    data class EmailOrPhoneChanged(val inputEmailOrPhoneValue: String) : SignUpUIEvent()
    data class PasswordChanged(val inputPasswordValue: String) : SignUpUIEvent()
    data class ConfirmPasswordChanged(val inputConfirmPasswordValue: String) : SignUpUIEvent()
    object OnSubmit : SignUpUIEvent()
}