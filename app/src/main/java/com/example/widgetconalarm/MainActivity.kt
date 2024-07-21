package com.example.widgetconalarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.widgetconalarm.models.AlarmItem
import com.example.widgetconalarm.models.AlarmItemList
import com.example.widgetconalarm.receivers.AlarmReceiver
import java.time.DayOfWeek
import java.time.LocalDateTime


class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi", "UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scheduler = AndroidAlarmScheduler(this)
        var alarmItem: AlarmItem? = null
        var alarmReceiver: AlarmReceiver? = null
        val hora = LocalDateTime.now()

//        runBlocking { HoraWidget().updateAll(this@MainActivity) }
        setContent {

            MaterialTheme {
                var dayOfWeekText by remember { mutableStateOf("") }
                var hourText by remember { mutableStateOf("${hora.hour}") }
                var minuteText by remember { mutableStateOf("${hora.minute + 1}") }
                var message by remember { mutableStateOf("Se lanzo: ${hora.hour}:${hora.minute}:${hora.second}, Dia: ${DayOfWeek.from(hora).name}") }
                dayOfWeekText = DayOfWeek.from(LocalDateTime.now()).name


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {

//                    Text(text = "Hora: ${hora.hour}:${hora.minute}:${hora.second}, Dia: ${DayOfWeek.from(hora).name}")
                    OutlinedTextField(
                        value = dayOfWeekText,
                        onValueChange = { dayOfWeekText = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Enter day of week (e.g., MONDAY)") }
                    )
                    OutlinedTextField(
                        value = hourText,
                        onValueChange = { hourText = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Enter hour (0-23)") }
                    )
                    OutlinedTextField(
                        value = minuteText,
                        onValueChange = { minuteText = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Enter minute (0-59)") }
                    )
                    OutlinedTextField(
                        value = message,
                        onValueChange = { message = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Enter message") }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = {
                            val dayOfWeek = DayOfWeek.valueOf(dayOfWeekText.uppercase())
                            val hour = hourText.toInt()
                            val minute = minuteText.toInt()

                            alarmItem = AlarmItem(
                                dayOfWeek = dayOfWeek,
                                hour = hour,
                                minute = minute,
                                message = message
                            )
                            println("Hora: ${alarmItem!!.hour}:${alarmItem!!.minute}")
//                            alarmItem?.let(scheduler::schedule)
                            AlarmItemList().state.forEach {
                                it.let(scheduler::schedule)
                            }
//                            dayOfWeekText = ""
//                            hourText = ""
//                            minuteText = ""
//                            message = ""
                        }) {
                            Text(text = "Schedule")
                        }
                        Button(onClick = {

                            val intent = Intent(this@MainActivity, AlarmReceiver::class.java)
                            val alarmExists = checkAlarmExists(this@MainActivity, 0, intent)
                            if (alarmExists) {
                                Log.d("existe", "Si existe")
                            } else {
                                Log.d("existe", "No existe")
                            }
//                            alarmItem?.let(scheduler::cancel)
//                            Log.d("p2", "Se cancelo")
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnspecifiedImmutableFlag")
fun checkAlarmExists(context: Context, requestCode: Int, intent: Intent): Boolean {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_NO_CREATE)
    return pendingIntent != null
}

