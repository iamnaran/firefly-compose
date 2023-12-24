package com.iamnaran.firefly.ui.common

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<T>(val successMessage: String, val data: T) : ViewState<T>()
    data class Error(val errorMessage: String) : ViewState<Nothing>()
}
