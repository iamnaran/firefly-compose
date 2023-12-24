package com.iamnaran.firefly.ui.base

sealed class ScreenState<out T> {
    object Initial : ScreenState<Nothing>()
    object Loading : ScreenState<Nothing>()
    data class Success<T>(val successMessage:String, val data: T) : ScreenState<T>()
    data class Error(val errorMessage: String) : ScreenState<Nothing>()
}
