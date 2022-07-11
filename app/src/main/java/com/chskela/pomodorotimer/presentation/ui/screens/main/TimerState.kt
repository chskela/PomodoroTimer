package com.chskela.pomodorotimer.presentation.ui.screens.main

sealed class TimerState {
    object Running : TimerState()
    object Stop : TimerState()
    data class Pause(val time: Long) : TimerState()
}
