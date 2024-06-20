package com.example.jetpackcompose

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CounterReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val service = NotificationService(context)
        service.showNotification(++Counter.value)
    }
}