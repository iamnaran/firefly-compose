package com.iamnaran.firefly.ui.login

import com.iamnaran.firefly.domain.login.model.LoginResponse

class LoginContract {
    data class State(
        val userLoggedInResponse: LoginResponse,
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
    }
}