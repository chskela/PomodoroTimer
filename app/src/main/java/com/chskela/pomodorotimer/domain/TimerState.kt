package com.chskela.pomodorotimer.domain

sealed class TimerState {
    object Running : TimerState()
    object Stop : TimerState()
    data class Pause(val time: Long) : TimerState()
}
