package com.example.widgetconalarm.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.widgetconalarm.AndroidAlarmScheduler
import com.example.widgetconalarm.models.AlarmItemList


class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val scheduler = AndroidAlarmScheduler(context)
        if(intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            AlarmItemList().state.forEach {
                it.let(scheduler::schedule)
            }
        }
    }
}