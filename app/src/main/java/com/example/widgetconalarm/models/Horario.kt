package com.example.widgetconalarm.models

import androidx.compose.ui.graphics.Color
import com.example.widgetconalarm.WidgetType


data class Horario(
    val entrada: String,
    val salida: String,
    val materia: String,
    val aula: String,
    val profesor: String,
    val highlight: Color,
    val tipo: WidgetType
)
