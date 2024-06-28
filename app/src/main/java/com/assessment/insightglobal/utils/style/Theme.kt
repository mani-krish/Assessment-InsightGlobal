package com.assessment.insightglobal.utils.style

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColors = lightColorScheme(
    primary = com.assessment.insightglobal.presentation.theme.md_theme_light_primary,
    onPrimary = com.assessment.insightglobal.presentation.theme.md_theme_light_onPrimary,
    primaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_light_primaryContainer,
    onPrimaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_light_onPrimaryContainer,
    secondary = com.assessment.insightglobal.presentation.theme.md_theme_light_secondary,
    onSecondary = com.assessment.insightglobal.presentation.theme.md_theme_light_onSecondary,
    secondaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_light_secondaryContainer,
    onSecondaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_light_onSecondaryContainer,
    tertiary = com.assessment.insightglobal.presentation.theme.md_theme_light_tertiary,
    onTertiary = com.assessment.insightglobal.presentation.theme.md_theme_light_onTertiary,
    tertiaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_light_tertiaryContainer,
    onTertiaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_light_onTertiaryContainer,
    error = com.assessment.insightglobal.presentation.theme.md_theme_light_error,
    errorContainer = com.assessment.insightglobal.presentation.theme.md_theme_light_errorContainer,
    onError = com.assessment.insightglobal.presentation.theme.md_theme_light_onError,
    onErrorContainer = com.assessment.insightglobal.presentation.theme.md_theme_light_onErrorContainer,
    background = com.assessment.insightglobal.presentation.theme.md_theme_light_background,
    onBackground = com.assessment.insightglobal.presentation.theme.md_theme_light_onBackground,
    surface = com.assessment.insightglobal.presentation.theme.md_theme_light_surface,
    onSurface = com.assessment.insightglobal.presentation.theme.md_theme_light_onSurface,
    surfaceVariant = com.assessment.insightglobal.presentation.theme.md_theme_light_surfaceVariant,
    onSurfaceVariant = com.assessment.insightglobal.presentation.theme.md_theme_light_onSurfaceVariant,
    outline = com.assessment.insightglobal.presentation.theme.md_theme_light_outline,
    inverseOnSurface = com.assessment.insightglobal.presentation.theme.md_theme_light_inverseOnSurface,
    inverseSurface = com.assessment.insightglobal.presentation.theme.md_theme_light_inverseSurface,
    inversePrimary = com.assessment.insightglobal.presentation.theme.md_theme_light_inversePrimary,
    surfaceTint = com.assessment.insightglobal.presentation.theme.md_theme_light_surfaceTint,
    outlineVariant = com.assessment.insightglobal.presentation.theme.md_theme_light_outlineVariant,
    scrim = com.assessment.insightglobal.presentation.theme.md_theme_light_scrim,
)

private val DarkColors = darkColorScheme(
    primary = com.assessment.insightglobal.presentation.theme.md_theme_dark_primary,
    onPrimary = com.assessment.insightglobal.presentation.theme.md_theme_dark_onPrimary,
    primaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_dark_primaryContainer,
    onPrimaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_dark_onPrimaryContainer,
    secondary = com.assessment.insightglobal.presentation.theme.md_theme_dark_secondary,
    onSecondary = com.assessment.insightglobal.presentation.theme.md_theme_dark_onSecondary,
    secondaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_dark_secondaryContainer,
    onSecondaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_dark_onSecondaryContainer,
    tertiary = com.assessment.insightglobal.presentation.theme.md_theme_dark_tertiary,
    onTertiary = com.assessment.insightglobal.presentation.theme.md_theme_dark_onTertiary,
    tertiaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_dark_tertiaryContainer,
    onTertiaryContainer = com.assessment.insightglobal.presentation.theme.md_theme_dark_onTertiaryContainer,
    error = com.assessment.insightglobal.presentation.theme.md_theme_dark_error,
    errorContainer = com.assessment.insightglobal.presentation.theme.md_theme_dark_errorContainer,
    onError = com.assessment.insightglobal.presentation.theme.md_theme_dark_onError,
    onErrorContainer = com.assessment.insightglobal.presentation.theme.md_theme_dark_onErrorContainer,
    background = com.assessment.insightglobal.presentation.theme.md_theme_dark_background,
    onBackground = com.assessment.insightglobal.presentation.theme.md_theme_dark_onBackground,
    surface = com.assessment.insightglobal.presentation.theme.md_theme_dark_surface,
    onSurface = com.assessment.insightglobal.presentation.theme.md_theme_dark_onSurface,
    surfaceVariant = com.assessment.insightglobal.presentation.theme.md_theme_dark_surfaceVariant,
    onSurfaceVariant = com.assessment.insightglobal.presentation.theme.md_theme_dark_onSurfaceVariant,
    outline = com.assessment.insightglobal.presentation.theme.md_theme_dark_outline,
    inverseOnSurface = com.assessment.insightglobal.presentation.theme.md_theme_dark_inverseOnSurface,
    inverseSurface = com.assessment.insightglobal.presentation.theme.md_theme_dark_inverseSurface,
    inversePrimary = com.assessment.insightglobal.presentation.theme.md_theme_dark_inversePrimary,
    surfaceTint = com.assessment.insightglobal.presentation.theme.md_theme_dark_surfaceTint,
    outlineVariant = com.assessment.insightglobal.presentation.theme.md_theme_dark_outlineVariant,
    scrim = com.assessment.insightglobal.presentation.theme.md_theme_dark_scrim,
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val context = LocalContext.current
    val colors = when {
        (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) -> {
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColors
        else -> LightColors
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = colors.primary
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colors.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = com.assessment.insightglobal.presentation.theme.typography,
        shapes = com.assessment.insightglobal.presentation.theme.shapes,
        content = content
    )
}