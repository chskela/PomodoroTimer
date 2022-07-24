package com.chskela.pomodorotimer.presentation.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

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