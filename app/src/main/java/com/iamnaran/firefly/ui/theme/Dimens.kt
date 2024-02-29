package com.iamnaran.firefly.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val default: Dp = 2.dp,
    val extraSmall: Dp = 4.dp,
    val regular: Dp = 8.dp,
    val medium: Dp = 10.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 32.dp,

    val paddingDefault: Dp = 2.dp,
    val paddingSmall: Dp = 4.dp,
    val paddingRegular: Dp = 8.dp,
    val paddingExtraLarge: Dp = 32.dp,

)

val LocalDimens = compositionLocalOf { Dimens() }

val MaterialTheme.dimens: Dimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current