package com.chskela.pomodorotimer.presentation.ui.screens.main

import android.app.Application
import android.app.NotificationManager
import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.chskela.pomodorotimer.R
import com.chskela.pomodorotimer.util.Constants.LONG_BREAK
import com.chskela.pomodorotimer.util.Constants.ONE_SECOND
import com.chskela.pomodorotimer.util.Constants.SHORT_BREAK
import com.chskela.pomodorotimer.util.Constants.WORKING_PERIOD
import com.chskela.pomodorotimer.util.cancelNotifications
import com.chskela.pomodorotimer.util.createChannel
import com.chskela.pomodorotimer.util.sendNotification

class MainScreenViewModel(private val app: Application) : AndroidViewModel(app) {

    init {
        createChannel(
            app.applicationContext,
            app.getString(R.string.pomodoro_notification_channel_id),
            app.getString(R.string.pomodoro_notification_channel_name)
        )
    }

    private val notificationManager = ContextCompat.getSystemService(
        app,
        NotificationManager::class.java
    ) as NotificationManager

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
        notificationManager.cancelNotifications()

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
                            sendNotificationWithApp(app.getString(R.string.focus), R.drawable.brain)
                        }

                        is PomodoroState.LongBreak -> {
                            timer = getBreakTimer(time)
                            timer.start()
                            sendNotificationWithApp(
                                app.getString(R.string.long_break),
                                R.drawable.coffer
                            )
                        }

                        is PomodoroState.ShortBreak -> {
                            timer = getBreakTimer(time)
                            timer.start()
                            sendNotificationWithApp(
                                app.getString(R.string.short_break),
                                R.drawable.coffer
                            )
                        }
                    }
                } else {
                    when (pomodoroState.value) {
                        is PomodoroState.Focus -> {
                            repeatWorkPeriod++
                            timer = getWorkTimer()
                            timer.start()
                            sendNotificationWithApp(app.getString(R.string.focus), R.drawable.brain)
                        }

                        is PomodoroState.LongBreak -> {
                            timer = getBreakTimer(LONG_BREAK)
                            timer.start()
                            sendNotificationWithApp(
                                app.getString(R.string.long_break),
                                R.drawable.coffer
                            )
                        }

                        is PomodoroState.ShortBreak -> {
                            timer = getBreakTimer()
                            timer.start()
                            sendNotificationWithApp(
                                app.getString(R.string.short_break),
                                R.drawable.coffer
                            )
                        }
                    }
                }
            }

            MainScreenEvent.OnPause -> {
                isRunnable.value = true
                timerState = TimerState.Pause(time)
                timer.cancel()
                sendNotificationWithApp(
                    app.getString(R.string.pause),
                    R.drawable.pause
                )
            }
        }
    }

    private val sendNotificationWithApp = notificationManager.sendNotification(app)

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
            isRunnable.value = true
            onFinishHandler()
            notificationManager.cancelNotifications()
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