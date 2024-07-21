package com.example.widgetconalarm.models

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.util.Log
import com.example.widgetconalarm.AlarmItem
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

class AlarmHelper {
    @SuppressLint("ScheduleExactAlarm")
    fun enqueue(context: Context, alarm: AlarmItem, pendingIntent: PendingIntent) {


        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmInfo = AlarmManager.AlarmClockInfo(
            getAlarmTime(alarm),
            pendingIntent
        )
        Log.d("AlarmHelper", "Scheduling alarm time: ${Date(getAlarmTime(alarm))}")
        alarmManager.setAlarmClock(alarmInfo, pendingIntent)
    }

    fun getAlarmTime(alarm: AlarmItem): Long {
        val calendar = GregorianCalendar()
        val currentTime: Date = Calendar.getInstance().time
        calendar.time = currentTime

        // reset the calendar time to the start of the day
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        // add the milliseconds from the new alarm
        calendar.add(Calendar.HOUR_OF_DAY, alarm.hour)
        calendar.add(Calendar.MINUTE, alarm.minute)

        calendar.add(Calendar.DATE, getPostponeDays(alarm.hour, alarm.minute))
        return calendar.timeInMillis
    }
    fun getPostponeDays(hour: Int, minute: Int): Int {
        val currentCalendar = Calendar.getInstance()

        // Crear un calendario para la alarma
        val alarmCalendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        // Comparar la hora de la alarma con la hora actual
        return if (alarmCalendar.before(currentCalendar)) {
            // Si la hora de la alarma es menor a la actual, programar para el siguiente día
            1
        } else {
            // Si la hora de la alarma es mayor o igual a la actual, programar para hoy
            0
        }
    }

}