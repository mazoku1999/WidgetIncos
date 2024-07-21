package com.example.widgetconalarm.data

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.widgetconalarm.WidgetType
import com.example.widgetconalarm.models.Horario
import java.time.DayOfWeek
import java.time.LocalDate

class WidgetHorariosData {

    var horarioDia by mutableStateOf<List<Horario>>(emptyList())
        private set

    private val horarios = mapOf(
        "Lunes" to listOf(
            Horario("18:30", "19:40", "REDES DE COMPUTADORAS II", "LAB-3-MOVIL", "VASQUEZ MARCELO", Color(0xFFfaf4ec), WidgetType.WidgetFor6to7PM),
            Horario("19:40", "20:50", "PROGRAMACION III", "LAB-4-MOVIL", "FRONTANILLA RODRIGO", Color(0xFFf4eade), WidgetType.WidgetFor8to9PM),
            Horario("21:00", "22:10", "DISEÑO Y PROGRAMACION WEB II", "LAB-1-PB", "CACERES PAVEL", Color(0xFFdbcbbc), WidgetType.WidgetFor10to11PM)
        ),
        "Martes" to listOf(
            Horario("18:30", "19:40", "GESTION DE SOFTWARE", "LAB-4-MOVIL", "VERA RAUL", Color(0xFFfaecec), WidgetType.WidgetFor6to7PM),
            Horario("19:40", "20:50", "BASE DE DATOS II", "LAB-SECRETARIADO", "ALANEZ JOEL", Color(0xFFf4dfdf), WidgetType.WidgetFor8to9PM),
            Horario("21:00", "22:10", "TALLER DE MODALIDAD DE GRADUACION", "AULA 2 - 4", "FLORES EDSON", Color(0xFFe6ccd2), WidgetType.WidgetFor10to11PM)
        ),
        "Miércoles" to listOf(
            Horario("18:30", "19:40", "GESTION DE SOFTWARE", "LAB-4-MOVIL", "VERA RAUL", Color(0xFFf2eaf1), WidgetType.WidgetFor6to7PM),
            Horario("19:40", "20:50", "ANALISIS Y DISEÑO DE SISTEMAS II", "LAB-4-MOVIL", "ESCALERA DAVID", Color(0xFFddd0d9), WidgetType.WidgetFor8to9PM),
            Horario("21:00", "22:10", "BASE DE DATOS II", "LAB-SISTEMAS", "ALANEZ JOEL", Color(0xFFb9a7b1), WidgetType.WidgetFor10to11PM)
        ),
        "Jueves" to listOf(
            Horario("18:30", "19:40", "TALLER DE MODALIDAD DE GRADUACION", "AULA 1 - 7 (72)", "FLORES EDSON", Color(0xFFe9f1f4), WidgetType.WidgetFor6to7PM),
            Horario("19:40", "20:50", "EMPRENDIMIENTO PRODUCTIVO", "AULA 2 - 4", "CACERES PAVEL", Color(0xFFd3dfe5), WidgetType.WidgetFor8to9PM),
            Horario("21:00", "22:10", "ANALISIS Y DISEÑO DE SISTEMAS II", "AULA 2 - 4", "ESCALERA DAVID", Color(0xFFb3c1cb), WidgetType.WidgetFor10to11PM)
        ),
        "Viernes" to listOf(
            Horario("18:30", "19:40", "DISEÑO Y PROGRAMACION WEB II", "LAB-2-2DO PISO", "CACERES PAVEL", Color(0xFFf8f8f8), WidgetType.WidgetFor6to7PM),
            Horario("19:40", "20:50", "PROGRAMACION III", "LAB-4-MOVIL", "FRONTANILLA RODRIGO", Color(0xFFe5e5e5), WidgetType.WidgetFor8to9PM),
            Horario("21:00", "22:10", "REDES DE COMPUTADORAS II", "AULA 2 - 4", "VASQUEZ MARCELO", Color(0xFFcecece), WidgetType.WidgetFor10to11PM)
        ),
        "Sabado" to listOf(
            Horario("18:30", "19:40", Color(0xFFf8f8f8)),
            Horario("19:40", "20:50", Color(0xFFe5e5e5)),
            Horario("21:00", "22:10", Color(0xFFcecece))
        ),
        "Domingo" to listOf(
            Horario("18:30", "19:40", Color(0xFFf8f8f8)),
            Horario("19:40", "20:50", Color(0xFFe5e5e5)),
            Horario("21:00", "22:10", Color(0xFFcecece))
        )
    )



    @SuppressLint("NewApi")
    fun HorarioDelDia() {
        val dayOfWeek = LocalDate.now().dayOfWeek
        val dayString = when (dayOfWeek) {
            DayOfWeek.MONDAY -> "Lunes"
            DayOfWeek.TUESDAY -> "Martes"
            DayOfWeek.WEDNESDAY -> "Miércoles"
            DayOfWeek.THURSDAY -> "Jueves"
            DayOfWeek.FRIDAY -> "Viernes"
            DayOfWeek.SATURDAY -> "Sabado"
            DayOfWeek.SUNDAY -> "Domingo"
            else -> null
        }
        horarioDia = horarios[dayString] ?: emptyList()
    }
}
