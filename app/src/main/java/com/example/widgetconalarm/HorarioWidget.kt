package com.example.widgetconalarm
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.widgetconalarm.ui.theme.lexendDecaFamily

//
//data class ClassSchedule(
//    val className: String,
//    val professor: String,
//    val startTime: String,
//    val endTime: String,
//    val location: String,
//    val color: Color
//)
//
//val weeklySchedule = mapOf(
//    "Lun" to listOf(
//        ClassSchedule("Programación III", "Dr. Juan Pérez", "07:45", "09:15", "LAB-1", Color(0xFF4F46E5)),
//        ClassSchedule("Base de Datos II", "Ing. María García", "10:30", "12:00", "AULA-3", Color(0xFFEF4444)),
//        ClassSchedule("Redes II", "Dr. Carlos López", "14:30", "16:00", "LAB-4", Color(0xFF10B981))
//    ),
//    "Mar" to listOf(
//        ClassSchedule("Sistemas Operativos", "Ing. Ana Torres", "08:00", "09:30", "LAB-2", Color(0xFFF59E0B)),
//        ClassSchedule("Desarrollo Web", "Lic. Roberto Díaz", "11:00", "12:30", "AULA-5", Color(0xFF6366F1)),
//        ClassSchedule("Inteligencia Artificial", "Dra. Laura Sánchez", "15:00", "16:30", "LAB-3", Color(0xFF8B5CF6))
//    ),
//    "Mié" to listOf(
//        ClassSchedule("Seguridad Informática", "Dr. Pedro Martínez", "07:45", "09:15", "LAB-5", Color(0xFFEC4899)),
//        ClassSchedule("Cloud Computing", "Ing. Sofia Ruiz", "10:30", "12:00", "AULA-4", Color(0xFF14B8A6)),
//        ClassSchedule("Desarrollo Móvil", "Dr. Diego Castro", "14:30", "16:00", "LAB-1", Color(0xFFF97316))
//    ),
//    "Jue" to listOf(
//        ClassSchedule("Machine Learning", "Dra. Carmen Vega", "08:00", "09:30", "LAB-3", Color(0xFF8B5CF6)),
//        ClassSchedule("DevOps", "Ing. Luis Morales", "11:00", "12:30", "AULA-2", Color(0xFF06B6D4)),
//        ClassSchedule("Arquitectura SW", "Dr. Andrés Silva", "15:00", "16:30", "LAB-4", Color(0xFFEF4444))
//    ),
//    "Vie" to listOf(
//        ClassSchedule("Gestión de Proyectos", "Ing. Patricia Luna", "07:45", "09:15", "AULA-1", Color(0xFF10B981)),
//        ClassSchedule("Big Data", "Dr. Marcos Ríos", "10:30", "12:00", "LAB-2", Color(0xFF6366F1)),
//        ClassSchedule("Testing", "Ing. Elena Paz", "14:30", "16:00", "LAB-5", Color(0xFFF59E0B))
//    )
//)
//
//@Composable
//fun WeeklySchedule() {
//    val days = listOf("Lun", "Mar", "Mié", "Jue", "Vie")
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp)
//    ) {
//        Text(
//            "Horario",
//            style = TextStyle(
//                fontSize = 28.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF1E293B)
//            ),
//            fontFamily = lexendDecaFamily
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(24.dp)
//        ) {
//            days.forEach { day ->
//                item {
//                    DaySchedule(day, weeklySchedule[day] ?: emptyList())
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun DaySchedule(day: String, classes: List<ClassSchedule>) {
//    Column(
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        Text(
//            when(day) {
//                "Lun" -> "Lunes"
//                "Mar" -> "Martes"
//                "Mié" -> "Miércoles"
//                "Jue" -> "Jueves"
//                "Vie" -> "Viernes"
//                else -> day
//            },
//            style = TextStyle(
//                fontSize = 20.sp,
//                fontWeight = FontWeight.SemiBold,
//                color = Color(0xFF64748B)
//            ),
//            fontFamily = lexendDecaFamily
//        )
//
//        classes.forEach { classInfo ->
//            ClassCard(classInfo)
//        }
//    }
//}
//
//@Composable
//fun ClassCard(classInfo: ClassSchedule) {
//    Surface(
//        shape = RoundedCornerShape(16.dp),
//        color = Color.White,
//        shadowElevation = 2.dp,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(16.dp)
//                .height(IntrinsicSize.Min),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .width(4.dp)
//                    .fillMaxHeight()
//                    .clip(RoundedCornerShape(2.dp))
//                    .background(classInfo.color)
//            )
//
//            Column(
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                Text(
//                    classInfo.className,
//                    style = TextStyle(
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF1E293B)
//                    ),
//                    fontFamily = lexendDecaFamily
//                )
//
//                Text(
//                    classInfo.professor,
//                    style = TextStyle(
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = Color(0xFF64748B)
//                    ),
//                    fontFamily = lexendDecaFamily
//                )
//
//                Row(
//                    horizontalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.spacedBy(4.dp)
//                    ) {
//                        Icon(
//                            Icons.Filled.DateRange,
//                            contentDescription = null,
//                            tint = Color(0xFF64748B),
//                            modifier = Modifier.size(14.dp)
//                        )
//                        Text(
//                            "${classInfo.startTime} - ${classInfo.endTime}",
//                            style = TextStyle(
//                                fontSize = 12.sp,
//                                fontWeight = FontWeight.Medium,
//                                color = Color(0xFF64748B)
//                            ),
//                            fontFamily = lexendDecaFamily
//                        )
//                    }
//
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.spacedBy(4.dp)
//                    ) {
//                        Icon(
//                            Icons.Filled.Home,
//                            contentDescription = null,
//                            tint = Color(0xFF64748B),
//                            modifier = Modifier.size(14.dp)
//                        )
//                        Text(
//                            classInfo.location,
//                            style = TextStyle(
//                                fontSize = 12.sp,
//                                fontWeight = FontWeight.Medium,
//                                color = Color(0xFF64748B)
//                            ),
//                            fontFamily = lexendDecaFamily
//                        )
//                    }
//                }
//            }
//        }
//    }
//}