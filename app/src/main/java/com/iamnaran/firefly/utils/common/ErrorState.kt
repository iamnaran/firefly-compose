package com.iamnaran.firefly.utils.common

import androidx.annotation.StringRes
import com.iamnaran.firefly.R

data class ErrorState(
    val hasError: Boolean = false, val serverErrorMsg: String = ""
)