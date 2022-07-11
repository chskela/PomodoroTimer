package com.chskela.pomodorotimer.presentation.ui.components.chip

import androidx.annotation.DrawableRes
import com.chskela.pomodorotimer.R
import com.chskela.pomodorotimer.util.UiText

data class ChipUiState(
    val title: UiText = UiText.StringResource(R.string.focus),
    @DrawableRes val icon: Int = R.drawable.brain,
)
