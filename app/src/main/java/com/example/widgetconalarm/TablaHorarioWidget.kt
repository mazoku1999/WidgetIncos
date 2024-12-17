//package com.example.widgetconalarm
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.widgetconalarm.ui.theme.lexendDecaFamily
//
//
//data class ClassSchedule(
//    val name: String,
//    val professor: String,
//    val classroom: String,
//    val startHour: Int,
//    val startMinute: Int,
//    val duration: Int,
//    val color: Color
//)
//
//@Composable
//fun WeeklySchedule() {
//    val scheduleByDay = mapOf(
//        "Lun" to listOf(
//            ClassSchedule("Programación III", "Dr. Juan Pérez", "LAB-1", 8, 0, 90, Color(0xFF3B82F6)),
//            ClassSchedule("Base de Datos II", "Ing. Ana López", "LAB-3", 13, 30, 90, Color(0xFFF59E0B)),
//            ClassSchedule("Redes", "Msc. Carlos Ruiz", "AULA-4", 16, 0, 90, Color(0xFF10B981))
//        ),
//        "Mar" to listOf(
//            ClassSchedule("Diseño Web II", "Ing. María García", "LAB-2", 9, 30, 90, Color(0xFFEC4899)),
//            ClassSchedule("Sistemas Operativos", "Dr. Roberto Sánchez", "LAB-4", 14, 0, 90, Color(0xFF8B5CF6)),
//            ClassSchedule("Inteligencia Artificial", "Dra. Laura Mendoza", "AULA-2", 16, 30, 90, Color(0xFFEF4444))
//        ),
//        "Mié" to listOf(
//            ClassSchedule("Desarrollo Móvil", "Ing. Pedro Díaz", "LAB-1", 8, 0, 90, Color(0xFF14B8A6)),
//            ClassSchedule("Seguridad Informática", "Msc. Diana Torres", "LAB-3", 13, 30, 90, Color(0xFFF97316)),
//            ClassSchedule("Machine Learning", "Dr. Andrés Silva", "AULA-1", 16, 0, 90, Color(0xFF6366F1))
//        ),
//        "Jue" to listOf(
//            ClassSchedule("Arquitectura Software", "Dr. Miguel Ángel", "LAB-2", 9, 30, 90, Color(0xFFD946EF)),
//            ClassSchedule("Cloud Computing", "Ing. Sofia Vargas", "LAB-4", 14, 0, 90, Color(0xFF0EA5E9)),
//            ClassSchedule("Testing", "Msc. Jorge Morales", "AULA-3", 16, 30, 90, Color(0xFF84CC16))
//        ),
//        "Vie" to listOf(
//            ClassSchedule("DevOps", "Ing. Luis Castro", "LAB-1", 8, 0, 90, Color(0xFFEAB308)),
//            ClassSchedule("Blockchain", "Dra. Carmen Ruiz", "LAB-3", 13, 30, 90, Color(0xFF6366F1)),
//            ClassSchedule("Data Science", "Dr. Fernando Mesa", "AULA-2", 16, 0, 90, Color(0xFFEC4899))
//        )
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp)
//    ) {
//        ScheduleHeader()
//        Spacer(modifier = Modifier.height(24.dp))
//        ScheduleCalendar(scheduleByDay)
//    }
//}
//
//@Composable
//fun ScheduleHeader() {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            "Horario Completo",
//            style = TextStyle(
//                fontSize = 28.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF1E293B)
//            ),
//            fontFamily = lexendDecaFamily
//        )
//    }
//}
//
//@Composable
//fun ScheduleCalendar(scheduleByDay: Map<String, List<ClassSchedule>>) {
//    val days = listOf("Lun", "Mar", "Mié", "Jue", "Vie")
//
//    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(12.dp),
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        item {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Box(modifier = Modifier.width(50.dp))
//                days.forEach { day ->
//                    DayHeader(day)
//                }
//            }
//        }
//
//        items(12) { hour ->
//            TimeRow(hour = hour + 8, scheduleByDay)
//        }
//    }
//}
//
//@Composable
//fun DayHeader(day: String) {
//    Box(
//        modifier = Modifier.width(110.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Surface(
//            modifier = Modifier
//                .clip(RoundedCornerShape(12.dp))
//                .background(Color(0xFFF1F5F9)),
//            color = Color(0xFFF1F5F9)
//        ) {
//            Text(
//                day,
//                modifier = Modifier.padding(8.dp),
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color(0xFF1E293B)
//                ),
//                fontFamily = lexendDecaFamily
//            )
//        }
//    }
//}
//
//@Composable
//fun TimeRow(hour: Int, scheduleByDay: Map<String, List<ClassSchedule>>) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        Box(
//            modifier = Modifier.width(50.dp),
//            contentAlignment = Alignment.TopCenter
//        ) {
//            Text(
//                "${hour}:00",
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = Color(0xFF64748B)
//                ),
//                fontFamily = lexendDecaFamily
//            )
//        }
//
//        scheduleByDay.forEach { (day, classes) ->
//            ClassSlot(
//                classes.filter {
//                    it.startHour == hour
//                }.firstOrNull()
//            )
//        }
//    }
//}
//
//@Composable
//fun ClassSlot(classSchedule: ClassSchedule?) {
//    Box(
//        modifier = Modifier
//            .width(110.dp)
//            .fillMaxHeight()
//            .padding(horizontal = 4.dp)
//    ) {
//        if (classSchedule != null) {
//            Surface(
//                modifier = Modifier.fillMaxSize(),
//                color = classSchedule.color.copy(alpha = 0.2f),
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                Column(
//                    modifier = Modifier.padding(8.dp)
//                ) {
//                    Text(
//                        classSchedule.name,
//                        style = TextStyle(
//                            fontSize = 12.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = classSchedule.color
//                        ),
//                        maxLines = 2,
//                        overflow = TextOverflow.Ellipsis,
//                        fontFamily = lexendDecaFamily
//                    )
//                    Text(
//                        classSchedule.classroom,
//                        style = TextStyle(
//                            fontSize = 11.sp,
//                            fontWeight = FontWeight.Medium,
//                            color = Color(0xFF64748B)
//                        ),
//                        fontFamily = lexendDecaFamily
//                    )
//                    Text(
//                        "${classSchedule.startHour}:${classSchedule.startMinute.toString().padStart(2, '0')}",
//                        style = TextStyle(
//                            fontSize = 11.sp,
//                            fontWeight = FontWeight.Medium,
//                            color = Color(0xFF64748B)
//                        ),
//                        fontFamily = lexendDecaFamily
//                    )
//                }
//            }
//        }
//    }
//}