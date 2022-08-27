package com.chskela.pomodorotimer.presentation.ui.screens.main

sealed class MainScreenEvent{
    object OnStart : MainScreenEvent()
    object OnPause: MainScreenEvent()
    object OnReset: MainScreenEvent()
}
