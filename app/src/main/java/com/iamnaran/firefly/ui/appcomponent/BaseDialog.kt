package com.iamnaran.firefly.ui.appcomponent

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext


@Composable
fun BaseDialog(content: @Composable () -> Unit) {

    val statusBarLight = Color.WHITE
    val statusBarDark = Color.BLACK
    val navigationBarLight = Color.WHITE
    val navigationBarDark = Color.BLACK
    val isDarkMode = isSystemInDarkTheme()
    val context = LocalContext.current as ComponentActivity

    DisposableEffect(isDarkMode) {
        context.enableEdgeToEdge(
            statusBarStyle = if (!isDarkMode) {
                SystemBarStyle.light(
                    statusBarLight,
                    statusBarDark
                )
            } else {
                SystemBarStyle.dark(
                    statusBarDark
                )
            },
            navigationBarStyle = if (!isDarkMode) {
                SystemBarStyle.light(
                    navigationBarLight,
                    navigationBarDark
                )
            } else {
                SystemBarStyle.dark(navigationBarDark)
            }
        )

        onDispose { }
    }

    content()


}