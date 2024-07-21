package com.example.widgetconalarm

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDateTime

data class AlarmItem (
    val message: String,
    val dayOfWeek: DayOfWeek, // Añadido día de la semana
    val hour: Int, // Añadido hora
    val minute: Int, // Añadido minuto
    val repeat: Boolean = true // Añadido habilitar/deshabilitar
) : Serializable