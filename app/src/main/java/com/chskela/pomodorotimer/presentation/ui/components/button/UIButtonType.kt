package com.chskela.pomodorotimer.presentation.ui.components.button

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class UIButtonType(val height: Dp = 34.dp, val width: Dp = 34.dp, val radius: Dp = 8.dp) {
    object Small : UIButtonType()
    object Medium : UIButtonType(height = 80.dp, width = 80.dp, radius = 24.dp)
    object Large : UIButtonType(height = 96.dp, width = 128.dp, radius = 32.dp)
}
