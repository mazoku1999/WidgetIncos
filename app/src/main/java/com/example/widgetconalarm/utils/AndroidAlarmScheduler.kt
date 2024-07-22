package com.example.widgetconalarm.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.widgetconalarm.models.AlarmHelper
import com.example.widgetconalarm.models.AlarmItem
import com.example.widgetconalarm.models.AlarmScheduler
import com.example.widgetconalarm.receivers.AlarmReceiver

class AndroidAlarmScheduler(
    private val context: Context,
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    @SuppressLint("NewApi", "MissingPermission")
    override fun schedule(item: AlarmItem) {
        // Calcula la próxima fecha y hora para el día de la semana especificado
//        val now = LocalDateTime.now()
//        val nextAlarmTime = now.with(TemporalAdjusters.nextOrSame(item.dayOfWeek))
//            .withHour(item.hour)
//            .withMinute(item.minute)
//            .withSecond(0)
//            .withNano(0)

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", item)
        }
//        val intent = Intent(context, AlarmReceiver::class.java).apply {
//            putExtra("EXTRA_MESSAGE", item)
//        }

//        alarmManager.setExactAndAllowWhileIdle(
//            AlarmManager.RTC_WAKEUP,
//            nextAlarmTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
//            PendingIntent.getBroadcast(
//                context,
//                item.hashCode(),
//                intent,
//                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//            )
//        )

        // Programa la alarma
        AlarmHelper().enqueue(
            context, item, PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )

        // Programa la alarma con el método setAlarmClock

//        alarmManager.setAlarmClock(
//            AlarmManager.AlarmClockInfo(
//                nextAlarmTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
//                PendingIntent.getBroadcast(
//                    context,
//                    item.hashCode(),
//                    intent,
//                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//                )
//            ),
//            PendingIntent.getBroadcast(
//                context,
//                item.hashCode(),
//                intent,
//                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//            )
//        )
    }


    override fun cancel(item: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    fun scheduleAll(alarms: List<AlarmItem>) {

        alarms.forEach {
            Log.d("alarmas", "Alarma: ${it.dayOfWeek}")
            schedule(it)
        }
    }
}