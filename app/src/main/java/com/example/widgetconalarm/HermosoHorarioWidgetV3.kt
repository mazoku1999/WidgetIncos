//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.statusBarsPadding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.widgetconalarm.ui.theme.lexendDecaFamily
//
//
//data class ClassInfo(
//    val name: String,
//    val professor: String,
//    val location: String,
//    val duration: Int,
//    val backgroundColor: Color
//)
//
//@Composable
//fun ScheduleApp() {
//    var selectedDay by remember { mutableStateOf("Lun") }
//    val backgroundColor = Color(0xFF1A1A1A)
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = backgroundColor
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .statusBarsPadding()
//                .padding(top = 16.dp)
//        ) {
//            Header()
//            DaySelector(selectedDay) { selectedDay = it }
//            DailySchedule(selectedDay)
//        }
//    }
//}
//
//@Composable
//fun Header() {
//    Column(
//        modifier = Modifier.padding(horizontal = 20.dp)
//    ) {
//        Text(
//            "Horario",
//            style = TextStyle(
//                fontSize = 32.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White
//            ),
//            fontFamily = lexendDecaFamily
//        )
//        Text(
//            "Universidad Mayor de San Simón",
//            style = TextStyle(
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium,
//                color = Color.Gray
//            ),
//            fontFamily = lexendDecaFamily
//        )
//    }
//}
//
//@Composable
//fun DaySelector(selectedDay: String, onDaySelected: (String) -> Unit) {
//    val days = listOf(
//        "Lun" to "Lunes",
//        "Mar" to "Martes",
//        "Mié" to "Miércoles",
//        "Jue" to "Jueves",
//        "Vie" to "Viernes"
//    )
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 24.dp)
//            .horizontalScroll(rememberScrollState()),
//        horizontalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        Spacer(modifier = Modifier.width(20.dp))
//        days.forEach { (shortDay, fullDay) ->
//            DayButton(
//                shortDay = shortDay,
//                fullDay = fullDay,
//                isSelected = shortDay == selectedDay,
//                onClick = { onDaySelected(shortDay) }
//            )
//        }
//        Spacer(modifier = Modifier.width(4.dp))
//    }
//}
//
//@Composable
//fun DayButton(shortDay: String, fullDay: String, isSelected: Boolean, onClick: () -> Unit) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.clickable(onClick = onClick)
//    ) {
//        Box(
//            modifier = Modifier
//                .size(48.dp)
//                .clip(CircleShape)
//                .background(if (isSelected) Color(0xFFE4B7FF) else Color(0xFF2A2A2A)),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                shortDay,
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = if (isSelected) Color.Black else Color.White
//                ),
//                fontFamily = lexendDecaFamily
//            )
//        }
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(
//            fullDay,
//            style = TextStyle(
//                fontSize = 12.sp,
//                fontWeight = FontWeight.Medium,
//                color = if (isSelected) Color.White else Color.Gray
//            ),
//            fontFamily = lexendDecaFamily
//        )
//    }
//}
//
//@Composable
//fun DailySchedule(selectedDay: String) {
//    val schedule = getScheduleForDay(selectedDay)
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//            .padding(horizontal = 20.dp)
//    ) {
//        schedule.forEach { (time, classInfo) ->
//            TimeSlot(time, classInfo)
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//    }
//}
//
//@Composable
//fun TimeSlot(time: String, classInfo: ClassInfo?) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.Top
//    ) {
//        Text(
//            time,
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Medium,
//                color = Color.Gray
//            ),
//            modifier = Modifier.width(80.dp),
//            fontFamily = lexendDecaFamily
//        )
//
//        if (classInfo != null) {
//            ClassCard(classInfo)
//        }
//    }
//}
//
//@Composable
//fun ClassCard(classInfo: ClassInfo) {
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 8.dp),
//        shape = RoundedCornerShape(16.dp),
//        color = classInfo.backgroundColor
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(
//                classInfo.name,
//                style = TextStyle(
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                ),
//                fontFamily = lexendDecaFamily
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Text(
//                classInfo.professor,
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = Color.DarkGray
//                ),
//                fontFamily = lexendDecaFamily
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(4.dp)
//                ) {
//                    Icon(
//                        Icons.Filled.Home,
//                        contentDescription = null,
//                        tint = Color.DarkGray,
//                        modifier = Modifier.size(16.dp)
//                    )
//                    Text(
//                        classInfo.location,
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontWeight = FontWeight.Medium,
//                            color = Color.DarkGray
//                        ),
//                        fontFamily = lexendDecaFamily
//                    )
//                }
//
//                Surface(
//                    shape = RoundedCornerShape(12.dp),
//                    color = Color.Black.copy(alpha = 0.1f)
//                ) {
//                    Text(
//                        "${classInfo.duration} Min",
//                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
//                        style = TextStyle(
//                            fontSize = 12.sp,
//                            fontWeight = FontWeight.Medium,
//                            color = Color.DarkGray
//                        ),
//                        fontFamily = lexendDecaFamily
//                    )
//                }
//            }
//        }
//    }
//}
//
//fun getScheduleForDay(day: String): Map<String, ClassInfo> {
//    return when (day) {
//        "Lun" -> mapOf(
//            "18:30" to ClassInfo(
//                "Programación III",
//                "Dr. Juan Pérez",
//                "LAB-1",
//                70,
//                Color(0xFFB7E4FF)
//            ),
//            "19:40" to ClassInfo(
//                "Base de Datos II",
//                "Ing. Ana López",
//                "LAB-3",
//                70,
//                Color(0xFFFFE4B7)
//            ),
//            "21:00" to ClassInfo(
//                "Redes",
//                "Msc. Carlos Ruiz",
//                "AULA-4",
//                70,
//                Color(0xFFB7FFD8)
//            )
//        )
//        "Mar" -> mapOf(
//            "18:30" to ClassInfo(
//                "Diseño Web II",
//                "Ing. María García",
//                "LAB-2",
//                70,
//                Color(0xFFFFB7E4)
//            ),
//            "19:40" to ClassInfo(
//                "Sistemas Operativos",
//                "Dr. Roberto Sánchez",
//                "LAB-4",
//                70,
//                Color(0xFFE4B7FF)
//            ),
//            "21:00" to ClassInfo(
//                "Inteligencia Artificial",
//                "Dra. Laura Mendoza",
//                "AULA-2",
//                70,
//                Color(0xFFFFD8B7)
//            )
//        )
//        "Mié" -> mapOf(
//            "18:30" to ClassInfo(
//                "Desarrollo Móvil",
//                "Ing. Pedro Díaz",
//                "LAB-1",
//                70,
//                Color(0xFFB7FFE4)
//            ),
//            "19:40" to ClassInfo(
//                "Seguridad Informática",
//                "Msc. Diana Torres",
//                "LAB-3",
//                70,
//                Color(0xFFD8B7FF)
//            ),
//            "21:00" to ClassInfo(
//                "Machine Learning",
//                "Dr. Andrés Silva",
//                "AULA-1",
//                70,
//                Color(0xFFE4FFB7)
//            )
//        )
//        "Jue" -> mapOf(
//            "18:30" to ClassInfo(
//                "Arquitectura Software",
//                "Dr. Miguel Ángel",
//                "LAB-2",
//                70,
//                Color(0xFFFFB7D8)
//            ),
//            "19:40" to ClassInfo(
//                "Cloud Computing",
//                "Ing. Sofia Vargas",
//                "LAB-4",
//                70,
//                Color(0xFFB7D8FF)
//            ),
//            "21:00" to ClassInfo(
//                "Testing",
//                "Msc. Jorge Morales",
//                "AULA-3",
//                70,
//                Color(0xFFD8FFB7)
//            )
//        )
//        "Vie" -> mapOf(
//            "18:30" to ClassInfo(
//                "DevOps",
//                "Ing. Luis Castro",
//                "LAB-1",
//                70,
//                Color(0xFFFFE4B7)
//            ),
//            "19:40" to ClassInfo(
//                "Blockchain",
//                "Dra. Carmen Ruiz",
//                "LAB-3",
//                70,
//                Color(0xFFB7FFE4)
//            ),
//            "21:00" to ClassInfo(
//                "Data Science",
//                "Dr. Fernando Mesa",
//                "AULA-2",
//                70,
//                Color(0xFFE4B7FF)
//            )
//        )
//        else -> mapOf()
//    }
//}