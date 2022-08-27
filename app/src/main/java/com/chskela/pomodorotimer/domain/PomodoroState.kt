package com.chskela.pomodorotimer.domain

sealed class PomodoroState() {
    object LongBreak : PomodoroState()
    object ShortBreak : PomodoroState()
    object Focus : PomodoroState()
  }
