package com.chskela.pomodorotimer.presentation.ui.screens.main

import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chskela.pomodorotimer.util.Constants.LONG_BREAK
import com.chskela.pomodorotimer.util.Constants.ONE_SECOND
import com.chskela.pomodorotimer.util.Constants.SHORT_BREAK
import com.chskela.pomodorotimer.util.Constants.WORKING_PERIOD

class MainScreenViewModel : ViewModel() {

    var minutes: MutableState<String> = mutableStateOf("25")
        private set

    var seconds: MutableState<String> = mutableStateOf("00")
        private set

    private var repeat: Int =  0

    private val shortBreak = object : CountDownTimer(SHORT_BREAK, ONE_SECOND) {
        override fun onTick(p0: Long) {
            periodToUi(p0)
        }

        override fun onFinish() {

        }
    }

    private val longBreak = object : CountDownTimer(LONG_BREAK, ONE_SECOND) {
        override fun onTick(p0: Long) {
            periodToUi(p0)
        }

        override fun onFinish() {

        }
    }

    private val workingPeriod = object : CountDownTimer(WORKING_PERIOD, ONE_SECOND) {
        override fun onTick(p0: Long) {
            periodToUi(p0)
        }

        override fun onFinish() {
            repeat++

            if (repeat % 4 ==0) {
                longBreak.start()
            } else {
                shortBreak.start()
            }
        }
    }

    fun startWorkingPeriod() {
        workingPeriod.start()
    }

    private fun periodToUi(p: Long) {
        val allSeconds = p / 1000
        seconds.value = format(allSeconds % 60)
        minutes.value = format(allSeconds / 60)
    }

    private fun format(value: Long) = if (value <= 9) "0$value" else "$value"
}