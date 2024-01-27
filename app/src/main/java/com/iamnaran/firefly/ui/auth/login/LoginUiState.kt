package com.iamnaran.firefly.ui.auth.login

import androidx.compose.runtime.Stable
import com.iamnaran.firefly.domain.dto.UserResponse

@Stable
sealed class LoginUiState {
    object Initial: LoginUiState()
    object Loading: LoginUiState()
    data class Error(val errorMsg: String = ""): LoginUiState()
    data class EmailUiState(val email:String = ""): LoginUiState()
    data class PasswordUiState(val password:String = ""): LoginUiState()
    data class NavigateToHome(val userResponse: UserResponse) : LoginUiState()
    data class ShowErrorMessage(val message: String = ""): LoginUiState()

    data class IsLoading(val errorMsg: Boolean = false): LoginUiState()


}