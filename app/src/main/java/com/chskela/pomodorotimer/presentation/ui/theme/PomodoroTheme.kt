package com.chskela.pomodorotimer.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

enum class PomodoroColorScheme(
    val darkColorScheme: ColorScheme = BlueThemeDark,
    val lightColorScheme: ColorScheme = BlueThemeLight
) {
    BlueColorScheme(
        darkColorScheme = BlueThemeDark,
        lightColorScheme = BlueThemeLight
    ),

    GreenColorScheme(
        darkColorScheme = GreenThemeDark,
        lightColorScheme = GreenThemeLight
    ),

    RedColorScheme(
        darkColorScheme = RedThemeDark,
        lightColorScheme = RedThemeLight
    )
}


private val BlueThemeDark = darkColorScheme(
    primary = BlueAlpha600,
    onPrimary = Blue50,
    secondary = BlueAlpha100,
    onSecondary = Blue50,
    outline = Blue50,
    surface = Blue950,
    onSurface = Blue50
)

private val BlueThemeLight = lightColorScheme(
    primary = BlueAlpha600,
    onPrimary = Blue900,
    secondary = BlueAlpha100,
    onSecondary = Blue900,
    outline = Blue900,
    surface = Blue50,
    onSurface = Blue950
)

private val GreenThemeDark = darkColorScheme(
    primary = GreenAlpha600,
    onPrimary = Green50,
    secondary = GreenAlpha100,
    onSecondary = Green50,
    outline = Green50,
    surface = Green950,
    onSurface = Green50
)

private val GreenThemeLight = lightColorScheme(
    primary = GreenAlpha600,
    onPrimary = Green900,
    secondary = GreenAlpha100,
    onSecondary = Green900,
    outline = Green900,
    surface = Green50,
    onSurface = Green950
)
private val RedThemeDark = darkColorScheme(
    primary = RedAlpha700,
    onPrimary = Red50,
    secondary = RedAlpha100,
    onSecondary = Red50,
    outline = Red50,
    surface = Red950,
    onSurface = Red50
)

private val RedThemeLight = lightColorScheme(
    primary = RedAlpha700,
    onPrimary = Red900,
    secondary = RedAlpha100,
    onSecondary = Red900,
    outline = Red900,
    surface = Red50,
    onSurface = Red950
)

@Composable
fun PomodoroTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    lightColorScheme: ColorScheme = BlueThemeLight,
    darkColorScheme: ColorScheme = BlueThemeDark,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
