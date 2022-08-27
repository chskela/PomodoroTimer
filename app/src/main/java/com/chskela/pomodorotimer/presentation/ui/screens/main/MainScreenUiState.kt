package com.chskela.pomodorotimer.presentation.ui.screens.main

import com.chskela.pomodorotimer.domain.PomodoroState

data class MainScreenUiState(
    var pomodoroState: PomodoroState,
    var isRunnable: Boolean,
    var timerTime: String,
)
