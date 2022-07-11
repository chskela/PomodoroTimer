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

    var isRunnable: MutableState<Boolean> = mutableStateOf(true)
        private set

    var pomodoroState: MutableState<PomodoroState> = mutableStateOf(PomodoroState.Focus)
        private set
    private var timerState: TimerState = TimerState.Stop

    private var repeatWorkPeriod: Int = 0
    private var time: Long = 0

    private lateinit var timer: CountDownTimer

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            MainScreenEvent.OnStart -> {
                isRunnable.value = false
                timerState = TimerState.Running

                if (time != 0L) {
                    when (pomodoroState.value) {
                        is PomodoroState.Focus -> {
                            repeatWorkPeriod++
                            timer = getWorkTimer(time)
                            timer.start()
                        }

                        is PomodoroState.LongBreak -> {
                            timer = getBreakTimer(time)
                            timer.start()
                        }

                        is PomodoroState.ShortBreak -> {
                            timer = getBreakTimer(time)
                            timer.start()
                        }
                    }
                } else {
                    when (pomodoroState.value) {
                        is PomodoroState.Focus -> {
                            repeatWorkPeriod++
                            timer = getWorkTimer()
                            timer.start()
                        }

                        is PomodoroState.LongBreak -> {
                            timer = getBreakTimer(LONG_BREAK)
                            timer.start()
                        }

                        is PomodoroState.ShortBreak -> {
                            timer = getBreakTimer()
                            timer.start()
                        }
                    }
                }
            }

            MainScreenEvent.OnPause -> {
                isRunnable.value = true
                timerState = TimerState.Pause(time)
                timer.cancel()

            }
        }
    }

    private fun getTimer(
        time: Long,
        onFinishHandler: () -> Unit
    ) = object : CountDownTimer(time, ONE_SECOND) {

        override fun onTick(p0: Long) {
            periodToUi(p0)
            updateTime(p0)
        }

        override fun onFinish() {
            updateTime(0)
            timerState = TimerState.Stop
            onFinishHandler()
        }
    }

    private fun getWorkTimer(time: Long = WORKING_PERIOD) = getTimer(time) {
        pomodoroState.value = if (repeatWorkPeriod % 4 == 0) {
            periodToUi(LONG_BREAK)
            PomodoroState.LongBreak
        } else {
            periodToUi(SHORT_BREAK)
            PomodoroState.ShortBreak
        }
    }

    private fun getBreakTimer(time: Long = SHORT_BREAK) = getTimer(time) {
        periodToUi(WORKING_PERIOD)
        pomodoroState.value = PomodoroState.Focus
    }

    private fun periodToUi(p: Long) {
        val allSeconds = p / 1000
        seconds.value = format(allSeconds % 60)
        minutes.value = format(allSeconds / 60)
    }

    private fun format(value: Long) = if (value <= 9) "0$value" else "$value"

    private fun updateTime(value: Long) {
        time = value
    }
}