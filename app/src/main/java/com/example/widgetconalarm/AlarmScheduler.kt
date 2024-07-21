package com.example.widgetconalarm

import com.example.widgetconalarm.models.AlarmItem

interface AlarmScheduler {
    fun schedule(item: AlarmItem)
    fun cancel(item: AlarmItem)
}