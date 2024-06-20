package com.example.jetpackcompose

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat

class NotificationService(
    private val context: Context
) {

    fun showNotification(counter: Int) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val actionPendingIntent =
            PendingIntent.getBroadcast(
                context,
                2,
                Intent(context, CounterReceiver::class.java),
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
            )
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_timer_24)
            .setContentTitle("Increment Counter")
            .setContentText("The counter is: $counter")
            .setContentIntent(activityPendingIntent)
            .setSilent(true)
            .addAction(
                R.drawable.baseline_timer_24,
                "Increment",
                actionPendingIntent
            )
            .build()

        notificationManager.notify(1, notification)
    }


    companion object {
        const val NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID"
    }
}