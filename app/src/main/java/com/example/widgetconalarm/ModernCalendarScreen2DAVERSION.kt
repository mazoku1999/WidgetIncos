//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.style.TextOverflow
//import com.example.widgetconalarm.ui.theme.lexendDecaFamily
//import androidx.compose.animation.*
//import androidx.compose.animation.core.*
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.*
//import androidx.compose.foundation.shape.*
//
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material.icons.rounded.*
//
//import androidx.compose.ui.graphics.*
//import androidx.compose.ui.graphics.vector.ImageVector
//
//import java.time.LocalTime
//import java.time.format.DateTimeFormatter
//
//@RequiresApi(Build.VERSION_CODES.O)
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun EnhancedCalendarScreen() {
//    var selectedTab by remember { mutableStateOf(0) }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF0F172A))
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            ModernTopBar()
//
//            AnimatedContent(
//                targetState = selectedTab,
//                transitionSpec = {
//                    fadeIn() + slideInHorizontally() with
//                            fadeOut() + slideOutHorizontally()
//                }
//            ) { tab ->
//                when (tab) {
//                    0 -> ModernHomeScreen()
//                    1 -> ModernScheduleApp()
//                }
//            }
//        }
//
//        ModernBottomNavigation(
//            selectedTab = selectedTab,
//            onTabSelected = { selectedTab = it },
//            modifier = Modifier.align(Alignment.BottomCenter)
//        )
//    }
//}
//
//@Composable
//fun ModernTopBar() {
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .statusBarsPadding(),
//        color = Color(0xFF0F172A)
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(horizontal = 20.dp, vertical = 16.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column {
//                Text(
//                    "Facultad de Tecnología",
//                    style = TextStyle(
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = Color(0xFF94A3B8)
//                    ),
//                    fontFamily = lexendDecaFamily
//                )
//                Text(
//                    "Carlos Martinez",
//                    style = TextStyle(
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    ),
//                    fontFamily = lexendDecaFamily
//                )
//            }
//
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                IconButton(
//                    onClick = { },
//                    modifier = Modifier
//                        .size(44.dp)
//                        .clip(CircleShape)
//                        .background(Color(0xFF1E293B))
//                ) {
//                    Icon(
//                        Icons.Filled.Notifications,
//                        contentDescription = null,
//                        tint = Color.White
//                    )
//                }
//
//                Box(
//                    modifier = Modifier
//                        .size(44.dp)
//                        .clip(CircleShape)
//                        .background(
//                            Brush.linearGradient(
//                                listOf(Color(0xFF6366F1), Color(0xFF4F46E5))
//                            )
//                        ),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        "CM",
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.White
//                        ),
//                        fontFamily = lexendDecaFamily
//                    )
//                }
//            }
//        }
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun ModernHomeScreen() {
//    val scrollState = rememberScrollState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(scrollState)
//            .padding(20.dp),
//        verticalArrangement = Arrangement.spacedBy(24.dp)
//    ) {
//        LiveClassStatus()
//        UpcomingDeadlines()
//        DailyProgress()
//        TodayClasses()
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun LiveClassStatus() {
//    val currentTime = remember { LocalTime.now() }
//    val isInClass = remember { true } // Lógica real para determinar si está en clase
//
//    Surface(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(24.dp),
//        color = Color(0xFF1E293B)
//    ) {
//        Column(
//            modifier = Modifier.padding(20.dp)
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .size(8.dp)
//                            .clip(CircleShape)
//                            .background(if (isInClass) Color(0xFF34D399) else Color(0xFFFECA48))
//                    )
//                    Text(
//                        if (isInClass) "En clase" else "Próxima clase",
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontWeight = FontWeight.Medium,
//                            color = if (isInClass) Color(0xFF34D399) else Color(0xFFFECA48)
//                        ),
//                        fontFamily = lexendDecaFamily
//                    )
//                }
//
//                Text(
//                    currentTime.format(DateTimeFormatter.ofPattern("HH:mm")),
//                    style = TextStyle(
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        color = Color(0xFF94A3B8)
//                    ),
//                    fontFamily = lexendDecaFamily
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column {
//                    Text(
//                        "Programación III",
//                        style = TextStyle(
//                            fontSize = 24.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.White
//                        ),
//                        fontFamily = lexendDecaFamily
//                    )
//                    Text(
//                        "Dr. Juan Pérez",
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Medium,
//                            color = Color(0xFF94A3B8)
//                        ),
//                        fontFamily = lexendDecaFamily
//                    )
//                }
//
//                Surface(
//                    shape = RoundedCornerShape(16.dp),
//                    color = Color(0xFF374151)
//                ) {
//                    Row(
//                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
//                        horizontalArrangement = Arrangement.spacedBy(8.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            Icons.Rounded.LocationOn,
//                            contentDescription = null,
//                            tint = Color.White,
//                            modifier = Modifier.size(18.dp)
//                        )
//                        Text(
//                            "LAB-1",
//                            style = TextStyle(
//                                fontSize = 14.sp,
//                                fontWeight = FontWeight.SemiBold,
//                                color = Color.White
//                            ),
//                            fontFamily = lexendDecaFamily
//                        )
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            LinearProgressIndicator(
//                progress = 0.7f,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(8.dp)
//                    .clip(RoundedCornerShape(4.dp)),
//                color = Color(0xFF6366F1),
//                trackColor = Color(0xFF2D3748)
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                "45 minutos restantes",
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = Color(0xFF94A3B8)
//                ),
//                fontFamily = lexendDecaFamily
//            )
//        }
//    }
//}
//
//@Composable
//fun UpcomingDeadlines() {
//    Column(
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        Text(
//            "Próximas Entregas",
//            style = TextStyle(
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White
//            ),
//            fontFamily = lexendDecaFamily
//        )
//
//        Row(
//            modifier = Modifier
//                .horizontalScroll(rememberScrollState()),
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            DeadlineCard(
//                title = "Práctica 3",
//                subject = "Programación III",
//                daysLeft = 2,
//                color = Color(0xFF6366F1)
//            )
//            DeadlineCard(
//                title = "Proyecto Final",
//                subject = "Base de Datos II",
//                daysLeft = 5,
//                color = Color(0xFFF59E0B)
//            )
//            DeadlineCard(
//                title = "Exposición",
//                subject = "Redes",
//                daysLeft = 7,
//                color = Color(0xFF10B981)
//            )
//        }
//    }
//}
//
//@Composable
//fun DeadlineCard(
//    title: String,
//    subject: String,
//    daysLeft: Int,
//    color: Color
//) {
//    Surface(
//        modifier = Modifier.width(200.dp),
//        shape = RoundedCornerShape(20.dp),
//        color = color.copy(alpha = 0.1f)
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(40.dp)
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(color.copy(alpha = 0.2f)),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    Icons.Rounded.Done,
//                    contentDescription = null,
//                    tint = color,
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Text(
//                title,
//                style = TextStyle(
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White
//                ),
//                fontFamily = lexendDecaFamily
//            )
//
//            Text(
//                subject,
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = Color(0xFF94A3B8)
//                ),
//                fontFamily = lexendDecaFamily
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                "Entrega en $daysLeft días",
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = color
//                ),
//                fontFamily = lexendDecaFamily
//            )
//        }
//    }
//}
//
//@Composable
//fun DailyProgress() {
//    Surface(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(24.dp),
//        color = Color(0xFF1E293B)
//    ) {
//        Column(
//            modifier = Modifier.padding(20.dp)
//        ) {
//            Text(
//                "Progreso del Día",
//                style = TextStyle(
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White
//                ),
//                fontFamily = lexendDecaFamily
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                ProgressStat(
//                    icon = Icons.Rounded.Home,
//                    title = "Clases",
//                    value = "2/3",
//                    color = Color(0xFF6366F1)
//                )
//                ProgressStat(
//                    icon = Icons.Rounded.Done,
//                    title = "Tareas",
//                    value = "1/2",
//                    color = Color(0xFFF59E0B)
//                )
//                ProgressStat(
//                    icon = Icons.Rounded.DateRange,
//                    title = "Prácticas",
//                    value = "1/1",
//                    color = Color(0xFF10B981)
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun ProgressStat(
//    icon: ImageVector,
//    title: String,
//    value: String,
//    color: Color
//) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .size(48.dp)
//                .clip(RoundedCornerShape(16.dp))
//                .background(color.copy(alpha = 0.2f)),
//            contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                icon,
//                contentDescription = null,
//                tint = color,
//                modifier = Modifier.size(24.dp)
//            )
//        }
//
//        Text(
//            title,
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Medium,
//                color = Color(0xFF94A3B8)
//            ),
//            fontFamily = lexendDecaFamily
//        )
//
//        Text(
//            value,
//            style = TextStyle(
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White
//            ),
//            fontFamily = lexendDecaFamily
//        )
//    }
//}
//
//@Composable
//fun TodayClasses() {
//    Column(
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        Text(
//            "Clases de Hoy",
//            style = TextStyle(
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White
//            ),
//            fontFamily = lexendDecaFamily
//        )
//
//        Column(
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            ClassCard(
//                subject = "Base de Datos II",
//                professor = "Ing. Ana López",
//                time = "19:40 - 20:50",
//                location = "LAB-3",
//                color = Color(0xFFF59E0B),
//                isNext = true
//            )
//            ClassCard(
//                subject = "Redes",
//                professor = "Msc. Carlos Ruiz",
//                time = "21:00 - 22:10",
//                location = "AULA-4",
//                color = Color(0xFF10B981),
//                isNext = false
//            )
//        }
//    }
//}
//
//@Composable
//fun ClassCard(
//    subject: String,
//    professor: String,
//    time: String,
//    location: String,
//    color: Color,
//    isNext: Boolean
//) {
//    Surface(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(20.dp),
//        color = if (isNext) color.copy(alpha = 0.15f) else Color(0xFF1E293B)
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(48.dp)
//                        .clip(RoundedCornerShape(14.dp))
//                        .background(color.copy(alpha = 0.2f)),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        Icons.Rounded.Home,
//                        contentDescription = null,
//                        tint = color,
//                        modifier = Modifier.size(24.dp)
//                    )
//                }
//
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(4.dp)
//                ) {
//                    Text(
//                        subject,
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.White
//                        ),
//                        fontFamily = lexendDecaFamily
//                    )
//
//                    Text(
//                        professor,
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontWeight = FontWeight.Medium,
//                            color = Color(0xFF94A3B8)
//                        ),
//                        fontFamily = lexendDecaFamily
//                    )
//
//                    Row(
//                        horizontalArrangement = Arrangement.spacedBy(12.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Row(
//                            horizontalArrangement = Arrangement.spacedBy(4.dp),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Icon(
//                                Icons.Rounded.Home,
//                                contentDescription = null,
//                                tint = Color(0xFF94A3B8),
//                                modifier = Modifier.size(14.dp)
//                            )
//                            Text(
//                                time,
//                                style = TextStyle(
//                                    fontSize = 12.sp,
//                                    fontWeight = FontWeight.Medium,
//                                    color = Color(0xFF94A3B8)
//                                ),
//                                fontFamily = lexendDecaFamily
//                            )
//                        }
//
//                        Row(
//                            horizontalArrangement = Arrangement.spacedBy(4.dp),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Icon(
//                                Icons.Rounded.LocationOn,
//                                contentDescription = null,
//                                tint = Color(0xFF94A3B8),
//                                modifier = Modifier.size(14.dp)
//                            )
//                            Text(
//                                location,
//                                style = TextStyle(
//                                    fontSize = 12.sp,
//                                    fontWeight = FontWeight.Medium,
//                                    color = Color(0xFF94A3B8)
//                                ),
//                                fontFamily = lexendDecaFamily
//                            )
//                        }
//                    }
//                }
//            }
//
//            if (isNext) {
//                Surface(
//                    shape = CircleShape,
//                    color = color.copy(alpha = 0.2f)
//                ) {
//                    Icon(
//                        Icons.Rounded.ArrowForward,
//                        contentDescription = null,
//                        tint = color,
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .size(20.dp)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ModernBottomNavigation(
//    selectedTab: Int,
//    onTabSelected: (Int) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Surface(
//        modifier = modifier
//            .fillMaxWidth()
//            .navigationBarsPadding(),
//        color = Color(0xFF1E293B),
//        tonalElevation = 8.dp
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(horizontal = 24.dp, vertical = 12.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            BottomNavItem(
//                icon = Icons.Rounded.Home,
//                label = "Inicio",
//                isSelected = selectedTab == 0,
//                onClick = { onTabSelected(0) }
//            )
//            BottomNavItem(
//                icon = Icons.Rounded.DateRange,
//                label = "Horario",
//                isSelected = selectedTab == 1,
//                onClick = { onTabSelected(1) }
//            )
//            BottomNavItem(
//                icon = Icons.Rounded.Person,
//                label = "Perfil",
//                isSelected = selectedTab == 2,
//                onClick = { /* Implementar */ }
//            )
//        }
//    }
//}
//
//@Composable
//fun BottomNavItem(
//    icon: ImageVector,
//    label: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    val color = if (isSelected) Color(0xFF6366F1) else Color(0xFF94A3B8)
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .clickable(onClick = onClick)
//            .padding(8.dp)
//    ) {
//        Icon(
//            icon,
//            contentDescription = null,
//            tint = color,
//            modifier = Modifier.size(24.dp)
//        )
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Text(
//            label,
//            style = TextStyle(
//                fontSize = 12.sp,
//                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
//                color = color
//            ),
//            fontFamily = lexendDecaFamily
//        )
//    }
//}
//
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
//            Color(0xFFF8FAFC),
//            Color(0xFFF1F5F9)
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
//                color = Color(0xFF0F172A),
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
//                tint = Color(0xFF64748B),
//                modifier = Modifier.size(18.dp)
//            )
//            Text(
//                "Facultad de Ciencias y Tecnología",
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = Color(0xFF64748B)
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
//                        false -> SolidColor(Color(0xFFF1F5F9))
//                    }
//                )
//                .border(
//                    width = 1.dp,
//                    brush = Brush.linearGradient(
//                        colors = if (isSelected) {
//                            listOf(
//                                Color(0xFF8B5CF6).copy(alpha = 0.5f),
//                                Color(0xFF7C3AED).copy(alpha = 0.2f)
//                            )
//                        } else {
//                            listOf(
//                                Color(0xFF64748B).copy(alpha = 0.2f),
//                                Color(0xFF64748B).copy(alpha = 0.1f)
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
//                    color = if (isSelected) Color.White else Color(0xFF64748B)
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
//                color = if (isSelected) Color(0xFF6366F1) else Color(0xFF64748B)
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
//                    color = Color(0xFF64748B)
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
//        color = Color.White,
//
//    ) {
//        Box(
//            modifier = Modifier
//                .background(
//                    brush = Brush.linearGradient(
//                        listOf(
//                            classInfo.gradientColors[0].copy(alpha = 0.1f),
//                            classInfo.gradientColors[1].copy(alpha = 0.05f)
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
//                                color = Color(0xFF1E293B)
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
//                                color = Color(0xFF64748B)
//                            ),
//                            fontFamily = lexendDecaFamily
//                        )
//                    }
//
//                    Surface(
//                        shape = CircleShape,
//                        color = classInfo.iconTint.copy(alpha = 0.1f)
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
//                            tint = Color(0xFF64748B),
//                            modifier = Modifier.size(16.dp)
//                        )
//                        Text(
//                            classInfo.location,
//                            style = TextStyle(
//                                fontSize = 14.sp,
//                                fontWeight = FontWeight.Medium,
//                                color = Color(0xFF64748B)
//                            ),
//                            fontFamily = lexendDecaFamily
//                        )
//                    }
//
//                    Surface(
//                        shape = RoundedCornerShape(12.dp),
//                        color = Color(0xFFF1F5F9)
//                    ) {
//                        Row(
//                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
//                            horizontalArrangement = Arrangement.spacedBy(4.dp),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Icon(
//                                Icons.Rounded.Home,
//                                contentDescription = null,
//                                tint = Color(0xFF64748B),
//                                modifier = Modifier.size(14.dp)
//                            )
//                            Text(
//                                "${classInfo.duration} min",
//                                style = TextStyle(
//                                    fontSize = 13.sp,
//                                    fontWeight = FontWeight.Medium,
//                                    color = Color(0xFF64748B)
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
//
//
//
//
//
//
//
//
//
//
//
