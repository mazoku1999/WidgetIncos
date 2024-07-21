package com.example.widgetconalarm

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.widgetconalarm.data.WidgetHorariosData
import com.example.widgetconalarm.models.AlarmItemList
import com.example.widgetconalarm.models.Horario

import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.LocalDateTime
import java.time.LocalTime

private val widgetDataHolder = WidgetHorariosData()
class HoraWidget : GlanceAppWidget() {


    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            widgetDataHolder.HorarioDelDia()
//            LaunchedEffect(Unit) {
//                widgetDataHolder.HorarioDelDia()
//                println("HorarioDelDia launched, horarioDia: ${widgetDataHolder.horarioDia}")
//            }
//
            val alarmas by remember {
                mutableStateOf(AlarmItemList())
            }

            GlanceTheme {
//                Text(text = alarmas.state.value.get(1).message)
//                LazyColumn {
//                    items(alarmas.state.value) { alarmItem ->
//                        Column {
//                            Text(text = alarmItem.message)
//                            Text(text = alarmItem.dayOfWeek.toString())
//                            Text(text = alarmItem.hour.toString())
//                            Text(text = alarmItem.minute.toString())
//                        }
//                    }
//                }
                TimeBasedWidget()
//                HoraEnum()
            }
        }
    }
}


@Composable
fun HoraEnum() {
    val context = LocalContext.current
//    Toast.makeText(context, "Holaaa", Toast.LENGTH_SHORT).show()
//    var diaViewMode = remember { DayViewModel() }
    Log.d("gol","Entro al widget")
    var horaType by remember { mutableStateOf(HoraType.NOCHE) }
    var randomHoraType by remember { mutableStateOf(HoraType.entries.toTypedArray().random()) }
    println(randomHoraType)

////     Cambiar el estado después de 5 minutos
//    LaunchedEffect(Unit) {
//        delay(50000) // 5 minutos en milisegundos
//        horaType = HoraType.MANANA
//    }

    Scaffold {
        Box(
            contentAlignment = Alignment.Center,
            modifier = GlanceModifier.fillMaxSize()
        ) {
            when (randomHoraType) {
                HoraType.MANANA -> Text("Buenos días")
                HoraType.TARDE -> Text("Buenas tardes")
                HoraType.NOCHE -> Text("Buenas noches")
            }
        }
    }
}

enum class HoraType {
    TARDE, MANANA, NOCHE
}


///////////////////////////


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "NewApi")
@Composable
fun TimeBasedWidget() {
    val currentTime by remember { mutableStateOf(LocalDateTime.now()) }
    val widgetType by remember { mutableStateOf(getWidgetTypeForTime(currentTime).first) }
    Log.d("gol","Entro al widget")

    Scaffold {
        Box(
            modifier = GlanceModifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (widgetType) {
                WidgetType.WidgetFor6to7PM -> WidgetFor6to7PM(getWidgetTypeForTime(currentTime).second!!)
                WidgetType.WidgetFor8to9PM -> WidgetFor8to9PM(getWidgetTypeForTime(currentTime).second!!)
                WidgetType.WidgetFor10to11PM -> WidgetFor10to11PM(getWidgetTypeForTime(currentTime).second!!)
                WidgetType.WidgetRecreo -> WidgetRecreo()
                WidgetType.DefaultWidget -> DefaultWidget()
                WidgetType.WidgetNoHayClases -> WidgetNoHayClases()
            }
        }
    }
}

enum class WidgetType {
    WidgetFor6to7PM,
    WidgetFor8to9PM,
    WidgetFor10to11PM,
    WidgetRecreo,
    DefaultWidget,
    WidgetNoHayClases
}

@SuppressLint("NewApi")
fun getWidgetTypeForTime(time: LocalDateTime): Pair<WidgetType, Horario?> {

    val hour = time.hour
    val minute = time.minute


    if (widgetDataHolder.horarioDia.isEmpty()) {
        return Pair(WidgetType.WidgetNoHayClases, null)
    } else {
        val tipo: WidgetType
        var materia: Horario? = null
        val horario = widgetDataHolder.horarioDia.find {
            estaDentroDelIntervalo(it.entrada, it.salida)
        }

//        if (estaDentroDelIntervalo("01:00", "18:15") || estaDentroDelIntervalo("22:10", "23:59")) {
//            tipo = WidgetType.DefaultWidget
//        } else {
//            if (horario != null) {
//                tipo = horario.tipo
//                materia = horario
//            } else {
//                tipo = WidgetType.WidgetRecreo
//            }
//        }
        if (estaDentroDelIntervalo("18:30", "22:10")) {
            if (horario != null) {
                tipo = horario.tipo
                materia = horario
            } else {
                tipo = WidgetType.WidgetRecreo
            }
        } else {
            tipo = WidgetType.DefaultWidget
        }

        return Pair(tipo, materia)
    }
}

@Composable
fun WidgetFor6to7PM(horario: Horario) {
    val context = LocalContext.current
    Toast.makeText(context, "Toast desde Widget", Toast.LENGTH_SHORT).show()
    Text(text = horario.profesor)
}

@Composable
fun WidgetFor8to9PM(horario: Horario) {
    val context = LocalContext.current
    Toast.makeText(context, "Toast desde Widget", Toast.LENGTH_SHORT).show()
    Button(text = horario.profesor, onClick = { /*TODO*/ })
}

@Composable
fun WidgetFor10to11PM(horario: Horario) {
    val context = LocalContext.current
    Toast.makeText(context, "Toast desde Widget", Toast.LENGTH_SHORT).show()
    Button(text = horario.profesor, onClick = { /*TODO*/ })
}

@Composable
fun WidgetRecreo() {
    val context = LocalContext.current
    Toast.makeText(context, "Toast desde Widget", Toast.LENGTH_SHORT).show()
    Button(text = "Recreo", onClick = { /*TODO*/ })
}

@Composable
fun WidgetNoHayClases() {
    val context = LocalContext.current
    Toast.makeText(context, "Toast desde Widget", Toast.LENGTH_SHORT).show()
    Button(text = "No Hay Clases", onClick = { /*TODO*/ })
}

@Composable
fun DefaultWidget() {

    val context = LocalContext.current
    Text(text = "Fuera de Horario")
}

@SuppressLint("NewApi")
fun estaDentroDelIntervalo(inicio: String, fin: String): Boolean {
    val ahora = LocalTime.now()
    val horaInicio = LocalTime.parse(inicio)
    val horaFin = LocalTime.parse(fin)
    return ahora.isAfter(horaInicio) && ahora.isBefore(horaFin)
}


