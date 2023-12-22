package com.iamnaran.firefly.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Material 3 color schemes
private val appDarkColorScheme = darkColorScheme(
    primary = appDarkPrimary,
    onPrimary = appDarkOnPrimary,
    primaryContainer = appDarkPrimaryContainer,
    onPrimaryContainer = appDarkOnPrimaryContainer,
    inversePrimary = appDarkPrimaryInverse,
    secondary = appDarkSecondary,
    onSecondary = appDarkOnSecondary,
    secondaryContainer = appDarkSecondaryContainer,
    onSecondaryContainer = appDarkOnSecondaryContainer,
    tertiary = appDarkTertiary,
    onTertiary = appDarkOnTertiary,
    tertiaryContainer = appDarkTertiaryContainer,
    onTertiaryContainer = appDarkOnTertiaryContainer,
    error = appDarkError,
    onError = appDarkOnError,
    errorContainer = appDarkErrorContainer,
    onErrorContainer = appDarkOnErrorContainer,
    background = appDarkBackground,
    onBackground = appDarkOnBackground,
    surface = appDarkSurface,
    onSurface = appDarkOnSurface,
    inverseSurface = appDarkInverseSurface,
    inverseOnSurface = appDarkInverseOnSurface,
    surfaceVariant = appDarkSurfaceVariant,
    onSurfaceVariant = appDarkOnSurfaceVariant,
    outline = appDarkOutline
)

private val appLightColorScheme = lightColorScheme(
    primary = appLightPrimary,
    onPrimary = appLightOnPrimary,
    primaryContainer = appLightPrimaryContainer,
    onPrimaryContainer = appLightOnPrimaryContainer,
    inversePrimary = appLightPrimaryInverse,
    secondary = appLightSecondary,
    onSecondary = appLightOnSecondary,
    secondaryContainer = appLightSecondaryContainer,
    onSecondaryContainer = appLightOnSecondaryContainer,
    tertiary = appLightTertiary,
    onTertiary = appLightOnTertiary,
    tertiaryContainer = appLightTertiaryContainer,
    onTertiaryContainer = appLightOnTertiaryContainer,
    error = appLightError,
    onError = appLightOnError,
    errorContainer = appLightErrorContainer,
    onErrorContainer = appLightOnErrorContainer,
    background = appLightBackground,
    onBackground = appLightOnBackground,
    surface = appLightSurface,
    onSurface = appLightOnSurface,
    inverseSurface = appLightInverseSurface,
    inverseOnSurface = appLightInverseOnSurface,
    surfaceVariant = appLightSurfaceVariant,
    onSurfaceVariant = appLightOnSurfaceVariant,
    outline = appLightOutline
)

@Composable
fun FireflyComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val appColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> appDarkColorScheme
        else -> appLightColorScheme
    }

    MaterialTheme(
        colorScheme = appColorScheme,
        typography = appTypography,
        shapes = Shapes,
        content = content
    )
}
