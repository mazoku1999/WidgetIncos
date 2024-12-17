//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
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
//import androidx.compose.material.icons.filled.DateRange
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.rounded.Home
//import androidx.compose.material.icons.rounded.LocationOn
//import androidx.compose.material3.Icon
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.NavigationBarItemDefaults
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
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.SolidColor
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.widgetconalarm.ui.theme.lexendDecaFamily

//data class ClassInfo(
//    val name: String,
//    val professor: String,
//    val location: String,
//    val duration: Int,
//    val gradientColors: List<Color>,
//    val iconTint: Color
//)
//
//@Composable
//fun ModernScheduleApp() {
//    var selectedDay by remember { mutableStateOf("Lun") }
//    val backgroundGradient = Brush.verticalGradient(
//        colors = listOf(
//            Color(0xFF1A1A1A),
//            Color(0xFF2D2D2D)
//        )
//    )
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(brush = backgroundGradient)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .statusBarsPadding()
//                .padding(top = 24.dp)
//        ) {
//            ModernHeader()
//            AnimatedDaySelector(selectedDay) { selectedDay = it }
//            ModernDailySchedule(selectedDay)
//        }
//    }
//}
//
//@Composable
//fun ModernHeader() {
//    Column(
//        modifier = Modifier.padding(horizontal = 24.dp)
//    ) {
//        Text(
//            "Mi Horario",
//            style = TextStyle(
//                fontSize = 36.sp,
//                fontWeight = FontWeight.ExtraBold,
//                color = Color.White,
//                letterSpacing = (-1).sp
//            ),
//            fontFamily = lexendDecaFamily
//        )
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            Icon(
//                Icons.Filled.Home,
//                contentDescription = null,
//                tint = Color(0xFF6B7280),
//                modifier = Modifier.size(18.dp)
//            )
//            Text(
//                "Facultad de Ciencias y Tecnología",
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = Color(0xFF6B7280)
//                ),
//                fontFamily = lexendDecaFamily
//            )
//        }
//    }
//}
//
//@Composable
//fun AnimatedDaySelector(selectedDay: String, onDaySelected: (String) -> Unit) {
//    val days = listOf(
//        "Lun" to "Lunes",
//        "Mar" to "Martes",
//        "Mié" to "Miércoles",
//        "Jue" to "Jueves",
//        "Vie" to "Viernes"
//    )
//
//    Column(
//        modifier = Modifier.padding(vertical = 24.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .horizontalScroll(rememberScrollState())
//                .padding(horizontal = 24.dp),
//            horizontalArrangement = Arrangement.spacedBy(20.dp)
//        ) {
//            days.forEach { (shortDay, fullDay) ->
//                ModernDayButton(
//                    shortDay = shortDay,
//                    fullDay = fullDay,
//                    isSelected = shortDay == selectedDay,
//                    onClick = { onDaySelected(shortDay) }
//                )
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun ModernDayButton(
//    shortDay: String,
//    fullDay: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.clickable(onClick = onClick)
//    ) {
//        Box(
//            modifier = Modifier
//                .size(56.dp)
//                .clip(RoundedCornerShape(20.dp))
//                .background(
//                    brush = when (isSelected) {
//                        true -> Brush.linearGradient(
//                            colors = listOf(
//                                Color(0xFF8B5CF6),
//                                Color(0xFF7C3AED)
//                            )
//                        )
//                        false -> SolidColor(Color(0xFF2A2A2A))
//                    }
//                )
//                .border(
//                    width = 1.dp,
//                    brush = Brush.linearGradient(
//                        colors = if (isSelected) {
//                            listOf(
//                                Color.White.copy(alpha = 0.5f),
//                                Color.White.copy(alpha = 0.2f)
//                            )
//                        } else {
//                            listOf(
//                                Color.White.copy(alpha = 0.1f),
//                                Color.White.copy(alpha = 0.05f)
//                            )
//                        }
//                    ),
//                    shape = RoundedCornerShape(20.dp)
//                ),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                shortDay,
//                style = TextStyle(
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = if (isSelected) Color.White else Color(0xFF6B7280)
//                ),
//                fontFamily = lexendDecaFamily
//            )
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Text(
//            fullDay,
//            style = TextStyle(
//                fontSize = 13.sp,
//                fontWeight = FontWeight.Medium,
//                color = if (isSelected) Color.White else Color(0xFF6B7280)
//            ),
//            fontFamily = lexendDecaFamily
//        )
//    }
//}
//
//@Composable
//fun ModernDailySchedule(selectedDay: String) {
//    val schedule = getModernScheduleForDay(selectedDay)
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//            .padding(horizontal = 24.dp)
//    ) {
//        schedule.forEach { (time, classInfo) ->
//            ModernTimeSlot(time, classInfo)
//            Spacer(modifier = Modifier.height(20.dp))
//        }
//    }
//}
//
//@Composable
//fun ModernTimeSlot(time: String, classInfo: ClassInfo?) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.Top
//    ) {
//        Column(
//            horizontalAlignment = Alignment.End,
//            modifier = Modifier.width(80.dp)
//        ) {
//            Text(
//                time,
//                style = TextStyle(
//                    fontSize = 15.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color(0xFF6B7280)
//                ),
//                fontFamily = lexendDecaFamily
//            )
//        }
//
//        if (classInfo != null) {
//            ModernClassCard(classInfo)
//        }
//    }
//}
//
//@Composable
//fun ModernClassCard(classInfo: ClassInfo) {
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 16.dp),
//        shape = RoundedCornerShape(24.dp),
//        color = Color.Transparent
//    ) {
//        Box(
//            modifier = Modifier
//                .background(
//                    brush = Brush.linearGradient(classInfo.gradientColors),
//                    shape = RoundedCornerShape(24.dp)
//                )
//                .border(
//                    width = 1.dp,
//                    brush = Brush.linearGradient(
//                        listOf(
//                            Color.White.copy(alpha = 0.2f),
//                            Color.White.copy(alpha = 0.1f)
//                        )
//                    ),
//                    shape = RoundedCornerShape(24.dp)
//                )
//        ) {
//            Column(
//                modifier = Modifier.padding(20.dp)
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Column(modifier = Modifier.weight(1f)) {
//                        Text(
//                            classInfo.name,
//                            style = TextStyle(
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color.White
//                            ),
//                            fontFamily = lexendDecaFamily
//                        )
//
//                        Spacer(modifier = Modifier.height(4.dp))
//
//                        Text(
//                            classInfo.professor,
//                            style = TextStyle(
//                                fontSize = 14.sp,
//                                fontWeight = FontWeight.Medium,
//                                color = Color.White.copy(alpha = 0.7f)
//                            ),
//                            fontFamily = lexendDecaFamily
//                        )
//                    }
//
//                    Surface(
//                        shape = CircleShape,
//                        color = classInfo.iconTint.copy(alpha = 0.2f)
//                    ) {
//                        Icon(
//                            Icons.Filled.Home,
//                            contentDescription = null,
//                            tint = classInfo.iconTint,
//                            modifier = Modifier
//                                .padding(12.dp)
//                                .size(24.dp)
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.spacedBy(6.dp)
//                    ) {
//                        Icon(
//                            Icons.Rounded.LocationOn,
//                            contentDescription = null,
//                            tint = Color.White.copy(alpha = 0.7f),
//                            modifier = Modifier.size(16.dp)
//                        )
//                        Text(
//                            classInfo.location,
//                            style = TextStyle(
//                                fontSize = 14.sp,
//                                fontWeight = FontWeight.Medium,
//                                color = Color.White.copy(alpha = 0.7f)
//                            ),
//                            fontFamily = lexendDecaFamily
//                        )
//                    }
//
//                    Surface(
//                        shape = RoundedCornerShape(12.dp),
//                        color = Color.Black.copy(alpha = 0.2f)
//                    ) {
//                        Row(
//                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
//                            horizontalArrangement = Arrangement.spacedBy(4.dp),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Icon(
//                                Icons.Rounded.Home,
//                                contentDescription = null,
//                                tint = Color.White.copy(alpha = 0.7f),
//                                modifier = Modifier.size(14.dp)
//                            )
//                            Text(
//                                "${classInfo.duration} min",
//                                style = TextStyle(
//                                    fontSize = 13.sp,
//                                    fontWeight = FontWeight.Medium,
//                                    color = Color.White.copy(alpha = 0.7f)
//                                ),
//                                fontFamily = lexendDecaFamily
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//fun getModernScheduleForDay(day: String): Map<String, ClassInfo> {
//    return when (day) {
//        "Lun" -> mapOf(
//            "18:30" to ClassInfo(
//                "Programación III",
//                "Dr. Juan Pérez",
//                "LAB-1",
//                70,
//                listOf(Color(0xFF4F46E5), Color(0xFF4338CA)),
//                Color(0xFF818CF8)
//            ),
//            "19:40" to ClassInfo(
//                "Base de Datos II",
//                "Ing. Ana López",
//                "LAB-3",
//                70,
//                listOf(Color(0xFFEA580C), Color(0xFFC2410C)),
//                Color(0xFFFB923C)
//            ),
//            "21:00" to ClassInfo(
//                "Redes",
//                "Msc. Carlos Ruiz",
//                "AULA-4",
//                70,
//                listOf(Color(0xFF059669), Color(0xFF047857)),
//                Color(0xFF34D399)
//            )
//        )
//        "Mar" -> mapOf(
//            "18:30" to ClassInfo(
//                "Diseño Web II",
//                "Ing. María García",
//                "LAB-2",
//                70,
//                listOf(Color(0xFFDB2777), Color(0xFFBE185D)),
//                Color(0xFFF472B6)
//            ),
//            "19:40" to ClassInfo(
//                "Sistemas Operativos",
//                "Dr. Roberto Sánchez",
//                "LAB-4",
//                70,
//                listOf(Color(0xFF7C3AED), Color(0xFF6D28D9)),
//                Color(0xFFA78BFA)
//            ),
//            "21:00" to ClassInfo(
//                "Inteligencia Artificial",
//                "Dra. Laura Mendoza",
//                "AULA-2",
//                70,
//                listOf(Color(0xFFDC2626), Color(0xFFB91C1C)),
//                Color(0xFFFCA5A5)
//            )
//        )
//        "Mié" -> mapOf(
//            "18:30" to ClassInfo(
//                "Desarrollo Móvil",
//                "Ing. Pedro Díaz",
//                "LAB-1",
//                70,
//                listOf(Color(0xFF0D9488), Color(0xFF0F766E)),
//                Color(0xFF5EEAD4)
//            ),
//            "19:40" to ClassInfo(
//                "Seguridad Informática",
//                "Msc. Diana Torres",
//                "LAB-3",
//                70,
//                listOf(Color(0xFFEA580C), Color(0xFFC2410C)),
//                Color(0xFFFB923C)
//            ),
//            "21:00" to ClassInfo(
//                "Machine Learning",
//                "Dr. Andrés Silva",
//                "AULA-1",
//                70,
//                listOf(Color(0xFF4F46E5), Color(0xFF4338CA)),
//                Color(0xFF818CF8)
//            )
//        )
//        "Jue" -> mapOf(
//            "18:30" to ClassInfo(
//                "Arquitectura Software",
//                "Dr. Miguel Ángel",
//                "LAB-2",
//                70,
//                listOf(Color(0xFFD946EF), Color(0xFFC026D3)),
//                Color(0xFFF0ABFC)
//            ),
//            "19:40" to ClassInfo(
//                "Cloud Computing",
//                "Ing. Sofia Vargas",
//                "LAB-4",
//                70,
//                listOf(Color(0xFF0EA5E9), Color(0xFF0284C7)),
//                Color(0xFF7DD3FC)
//            ),
//            "21:00" to ClassInfo(
//                "Testing",
//                "Msc. Jorge Morales",
//                "AULA-3",
//                70,
//                listOf(Color(0xFF84CC16), Color(0xFF65A30D)),
//                Color(0xFFBEF264)
//            )
//        )
//        "Vie" -> mapOf(
//            "18:30" to ClassInfo(
//                "DevOps",
//                "Ing. Luis Castro",
//                "LAB-1",
//                70,
//                listOf(Color(0xFFF59E0B), Color(0xFFD97706)),
//                Color(0xFFFBD38D)
//            ),
//            "19:40" to ClassInfo(
//                "Blockchain",
//                "Dra. Carmen Ruiz",
//                "LAB-3",
//                70,
//                listOf(Color(0xFF6366F1), Color(0xFF4F46E5)),
//                Color(0xFFA5B4FC)
//            ),
//            "21:00" to ClassInfo(
//                "Data Science",
//                "Dr. Fernando Mesa",
//                "AULA-2",
//                70,
//                listOf(Color(0xFFEC4899), Color(0xFFDB2777)),
//                Color(0xFFF9A8D4)
//            )
//        )
//        else -> mapOf()
//    }
//}
//
//@Composable
//fun ModernScheduleNavigation() {
//    NavigationBar(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFF1A1A1A))
//            .padding(16.dp),
//        containerColor = Color.Transparent
//    ) {
//        NavigationBarItem(
//            icon = {
//                Icon(
//                    Icons.Filled.Notifications,
//                    contentDescription = null,
//                    modifier = Modifier.size(24.dp)
//                )
//            },
//            label = { Text("Horario") },
//            selected = true,
//            onClick = { },
//            colors = NavigationBarItemDefaults.colors(
//                selectedIconColor = Color(0xFF8B5CF6),
//                selectedTextColor = Color(0xFF8B5CF6),
//                unselectedIconColor = Color(0xFF6B7280),
//                unselectedTextColor = Color(0xFF6B7280),
//                indicatorColor = Color(0xFF2A2A2A)
//            )
//        )
//
//        NavigationBarItem(
//            icon = {
//                Icon(
//                    Icons.Filled.DateRange,
//                    contentDescription = null,
//                    modifier = Modifier.size(24.dp)
//                )
//            },
//            label = { Text("Calendario") },
//            selected = false,
//            onClick = { },
//            colors = NavigationBarItemDefaults.colors(
//                selectedIconColor = Color(0xFF8B5CF6),
//                selectedTextColor = Color(0xFF8B5CF6),
//                unselectedIconColor = Color(0xFF6B7280),
//                unselectedTextColor = Color(0xFF6B7280),
//                indicatorColor = Color(0xFF2A2A2A)
//            )
//        )
//
//        NavigationBarItem(
//            icon = {
//                Icon(
//                    Icons.Filled.Person,
//                    contentDescription = null,
//                    modifier = Modifier.size(24.dp)
//                )
//            },
//            label = { Text("Perfil") },
//            selected = false,
//            onClick = { },
//            colors = NavigationBarItemDefaults.colors(
//                selectedIconColor = Color(0xFF8B5CF6),
//                selectedTextColor = Color(0xFF8B5CF6),
//                unselectedIconColor = Color(0xFF6B7280),
//                unselectedTextColor = Color(0xFF6B7280),
//                indicatorColor = Color(0xFF2A2A2A)
//            )
//        )
//    }
//}
