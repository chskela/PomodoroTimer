package com.chskela.pomodorotimer.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.chskela.pomodorotimer.R

private const val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context){
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.pomodoro_notification_channel_id)
    )
        .setSmallIcon(R.drawable.brain)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)

    notify(NOTIFICATION_ID, builder.build())
}


fun createChannel(context: Context, channelId: String, channelName: String) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
            channelId, channelName, NotificationManager.IMPORTANCE_LOW
        )

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = "Time for Pomodoro"

        val notificationManager = context.getSystemService(NotificationManager::class.java)

        notificationManager.createNotificationChannel(notificationChannel)
    }
}