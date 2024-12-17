package com.example.widgetconalarm

import EnhancedCalendarScreen
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.appwidget.updateAll
import com.example.widgetconalarm.data.WidgetHorariosData
import com.example.widgetconalarm.models.AlarmItem
import com.example.widgetconalarm.models.AlarmItemList
import com.example.widgetconalarm.models.Horario
import com.example.widgetconalarm.receivers.AlarmReceiver
import com.example.widgetconalarm.utils.AndroidAlarmScheduler
import kotlinx.coroutines.runBlocking
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi", "UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val scheduler = AndroidAlarmScheduler(this)
        var alarmItem: AlarmItem? = null
        var alarmReceiver: AlarmReceiver? = null
        val hora = LocalDateTime.now()

//        runBlocking { HoraWidget().updateAll(this@MainActivity) }
        setContent {

            MaterialTheme {
                EnhancedCalendarScreen()
//                var dayOfWeekText by remember { mutableStateOf("") }
//                var hourText by remember { mutableStateOf("${hora.hour}") }
//                var minuteText by remember { mutableStateOf("${hora.minute + 1}") }
//                var message by remember { mutableStateOf("Se lanzo: ${hora.hour}:${hora.minute}:${hora.second}, Dia: ${DayOfWeek.from(hora).name}") }
//                dayOfWeekText = DayOfWeek.from(LocalDateTime.now()).name
//
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(text = "Widget Instituto")
//
////                    Text(text = "Hora: ${hora.hour}:${hora.minute}:${hora.second}, Dia: ${DayOfWeek.from(hora).name}")
//                    OutlinedTextField(
//                        value = dayOfWeekText,
//                        onValueChange = { dayOfWeekText = it },
//                        modifier = Modifier.fillMaxWidth(),
//                        placeholder = { Text(text = "Enter day of week (e.g., MONDAY)") }
//                    )
//                    OutlinedTextField(
//                        value = hourText,
//                        onValueChange = { hourText = it },
//                        modifier = Modifier.fillMaxWidth(),
//                        placeholder = { Text(text = "Enter hour (0-23)") }
//                    )
//                    OutlinedTextField(
//                        value = minuteText,
//                        onValueChange = { minuteText = it },
//                        modifier = Modifier.fillMaxWidth(),
//                        placeholder = { Text(text = "Enter minute (0-59)") }
//                    )
//                    OutlinedTextField(
//                        value = message,
//                        onValueChange = { message = it },
//                        modifier = Modifier.fillMaxWidth(),
//                        placeholder = { Text(text = "Enter message") }
//                    )
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Button(onClick = {
//                            val dayOfWeek = DayOfWeek.valueOf(dayOfWeekText.uppercase())
//                            val hour = hourText.toInt()
//                            val minute = minuteText.toInt()
//
//                            alarmItem = AlarmItem(
//                                dayOfWeek = dayOfWeek,
//                                hour = hour,
//                                minute = minute,
//                                message = message
//                            )
//                            println("Hora: ${alarmItem!!.hour}:${alarmItem!!.minute}")
////                            alarmItem?.let(scheduler::schedule)
//                            AlarmItemList().state.forEach {
//                                it.let(scheduler::schedule)
//                            }
////                            dayOfWeekText = ""
////                            hourText = ""
////                            minuteText = ""
////                            message = ""
//                        }) {
//                            Text(text = "Schedule")
//                        }
//                        Button(onClick = {
//
////                            alarmItem?.let(scheduler::cancel)
////                            Log.d("p2", "Se cancelo")
//                        }) {
//                            Text(text = "Cancel")
//                        }
//                    }
//                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UltraModernScheduleScreen(
    modifier: Modifier = Modifier,
    widgetHorariosData: WidgetHorariosData = WidgetHorariosData()
) {
    var selectedHorario by remember { mutableStateOf<Horario?>(null) }
    var showCalendar by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        widgetHorariosData.HorarioDelDia()
    }
    val surfaceColor = MaterialTheme.colorScheme.surface
    val surfaceColorAlpha = surfaceColor.copy(alpha = 0.95f)

    Box(modifier = modifier.fillMaxSize()) {
        // Background gradient
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        surfaceColor,
                        surfaceColorAlpha
                    )
                )
            )
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                UltraModernHeader(
                    onCalendarClick = { showCalendar = true }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Time Indicator
                CurrentTimeIndicator()

                // Schedule List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(widgetHorariosData.horarioDia) { horario ->
                        UltraModernScheduleCard(
                            horario = horario,
                            isSelected = selectedHorario == horario,
                            onClick = { selectedHorario = horario }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun UltraModernHeader(
    onCalendarClick: () -> Unit
) {
    val fechaActual = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("d 'de' MMMM", Locale("es"))
    val nombreDia = fechaActual.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("es"))

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
        tonalElevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = nombreDia.capitalize(),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-1).sp
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = fechaActual.format(formatter),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    )
                }

                // Modern Calendar Button
                IconButton(
                    onClick = onCalendarClick,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                )
                            )
                        )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        contentDescription = "Calendario",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CurrentTimeIndicator() {
    val currentTime = remember { LocalTime.now() }
    val animatedProgress by animateFloatAsState(
        targetValue = currentTime.hour * 60f + currentTime.minute,
        label = "timeProgress"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Hora actual",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    )
                    Text(
                        text = currentTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.5).sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = animatedProgress / (24f * 60f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            )
        }
    }
}

@Composable
private fun UltraModernScheduleCard(
    horario: Horario,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val cornerRadius = 24.dp

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(cornerRadius),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected)
                horario.highlight.copy(alpha = 0.3f)
            else
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        ),
        tonalElevation = if (isSelected) 8.dp else 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            horario.highlight.copy(alpha = 0.05f),
                            Color.Transparent
                        )
                    )
                )
        ) {
            // Time Stripe
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                horario.highlight.copy(alpha = 0.5f),
                                horario.highlight.copy(alpha = 0.0f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TimeChip(
                        startTime = horario.entrada,
                        endTime = horario.salida,
                        color = horario.highlight
                    )

                    if (horario.materia.isNotEmpty()) {
                        StatusIndicator(color = horario.highlight)
                    }
                }

                if (horario.materia.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = horario.materia,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.5).sp
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ModernInfoBox(
                        icon = Icons.Rounded.LocationOn,
                        text = "Aula ${horario.aula}",
                        color = horario.highlight
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ModernInfoBox(
                        icon = Icons.Rounded.Person,
                        text = horario.profesor,
                        color = horario.highlight
                    )
                } else {
                    FreeTimeIndicator()
                }
            }
        }
    }
}

@Composable
private fun TimeChip(
    startTime: String,
    endTime: String,
    color: Color
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = color.copy(alpha = 0.1f),
        border = BorderStroke(1.dp, color.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = color
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$startTime - $endTime",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = color
            )
        }
    }
}

@Composable
private fun StatusIndicator(color: Color) {
    Surface(
        shape = CircleShape,
        color = color.copy(alpha = 0.1f),
        border = BorderStroke(1.dp, color.copy(alpha = 0.2f)),
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Home,
            contentDescription = null,
            modifier = Modifier.padding(10.dp),
            tint = color
        )
    }
}

@Composable
private fun ModernInfoBox(
    icon: ImageVector,
    text: String,
    color: Color
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = color.copy(alpha = 0.05f)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = color.copy(alpha = 0.1f),
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp),
                    tint = color
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
private fun FreeTimeIndicator() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.2f)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f),
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Info,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Tiempo libre",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}