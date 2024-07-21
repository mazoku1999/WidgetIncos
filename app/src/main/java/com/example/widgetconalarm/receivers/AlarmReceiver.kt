package com.example.widgetconalarm.receivers

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.glance.appwidget.updateAll
import com.example.widgetconalarm.models.AlarmItem
import com.example.widgetconalarm.HoraWidget
import com.example.widgetconalarm.models.AlarmHelper
import kotlinx.coroutines.runBlocking

class AlarmReceiver: BroadcastReceiver() {



    @SuppressLint("NewApi")
    override fun onReceive(context: Context?, intent: Intent?) {
//        println("Ingreso al onReceive")
        val item = intent?.getSerializableExtra("EXTRA_MESSAGE", AlarmItem::class.java)
        val i = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", item)
        }
        if (item!!.repeat) {
            AlarmHelper().enqueue(context!!, item, PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                i,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            ))
            runBlocking {
                HoraWidget().updateAll(context)
            }
        }


        Log.d("AlarmReceiver", "Alarm received: ${item.repeat}")
    }
}