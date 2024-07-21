package com.example.widgetconalarm.models

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import com.example.widgetconalarm.data.WidgetHorariosData
import java.time.DayOfWeek

class AlarmItemList {

    @SuppressLint("NewApi")
    private var _state = mutableStateOf(
        listOf<AlarmItem>(
            AlarmItem(
                "08:00",
                DayOfWeek.MONDAY,
                18,
                30,
            ),
            AlarmItem(
                "09:00",
                DayOfWeek.TUESDAY,
                18,
                33,
            ),
            AlarmItem(
                "10:00",
                DayOfWeek.WEDNESDAY,
                18,
                36,
            ),
            AlarmItem(
                "11:00",
                DayOfWeek.THURSDAY,
                3,
                52,
            ),
            AlarmItem(
                "12:00",
                DayOfWeek.FRIDAY,
                3,
                53,
            ),
        ),
    )
    val state: List<AlarmItem> = obtenerAlarmas()
    @SuppressLint("NewApi")
    fun obtenerAlarmas(): List<AlarmItem> {



        val horario = WidgetHorariosData()
        horario.HorarioDelDia()
        val dias = horario.horarioDia.map { horas ->
            AlarmItem(
                horas.entrada,
                DayOfWeek.MONDAY,
                horas.entrada.split(":")[0].toInt(),
                horas.entrada.split(":")[1].toInt()
            )
        }.toMutableList()
        dias.add(
            AlarmItem(
                "08:00",
                DayOfWeek.MONDAY,
                22,
                10,
            )
        )
        return dias
    }

}