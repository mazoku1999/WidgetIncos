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
) {
    // Constructor secundario que solo toma algunos parámetros y usa valores predeterminados para otros
    constructor(entrada: String, salida: String, highlight:Color) : this(
        entrada = entrada,
        salida = salida,
        materia = "",
        aula = "",
        profesor = "",
        highlight = highlight,
        tipo = WidgetType.DefaultWidget
    )


}
