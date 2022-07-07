package com.chskela.pomodorotimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.chskela.pomodorotimer.presentation.PomodoroTimerApp
import com.chskela.pomodorotimer.presentation.ui.theme.PomodoroTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PomodoroTimerTheme {
               PomodoroTimerApp()
            }
        }
    }
}