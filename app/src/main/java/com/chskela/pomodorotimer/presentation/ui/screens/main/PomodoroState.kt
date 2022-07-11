package com.chskela.pomodorotimer.presentation.ui.screens.main

sealed class PomodoroState() {
    object LongBreak : PomodoroState()
    object ShortBreak : PomodoroState()
    object Focus : PomodoroState()
  }
