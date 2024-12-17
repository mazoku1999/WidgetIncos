import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.example.widgetconalarm.ui.theme.lexendDecaFamily
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*

import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned


import androidx.compose.ui.unit.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnhancedCalendarScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1A1A1A),
            Color(0xFF242424)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            TopBar()

            TabSection(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            AnimatedContent(
                targetState = selectedTab,
                transitionSpec = {
                    fadeIn(animationSpec = tween(300)) +
                            slideInHorizontally(animationSpec = tween(300)) with
                            fadeOut(animationSpec = tween(300)) +
                            slideOutHorizontally(animationSpec = tween(300))
                }
            ) { tab ->
                when (tab) {
                    0 -> ModernHomeScreen()
                    1 -> ModernScheduleApp() // Ya implementado anteriormente
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ModernHomeScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(14.dp)
    ) {
//        WelcomeSection()
//        Spacer(modifier = Modifier.height(14.dp))
        InstitutionalNoticesSection() // Movido arriba para dar más prioridad
        Spacer(modifier = Modifier.height(14.dp))
        DailyClassesSection()
    }
}


@Composable
fun InstitutionalNoticesSection() {
    var selectedNotice by remember { mutableStateOf<InstitutionalNotice?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Header con efecto de gradiente animado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Avisos",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,

                        ),
                    fontFamily = lexendDecaFamily
                )
                Text(
                    "Institucionales",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF8B5CF6),

                        ),
                    fontFamily = lexendDecaFamily
                )
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B5CF6).copy(alpha = 0.15f)
                ),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    "Ver todos",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF8B5CF6)
                    ),
                    fontFamily = lexendDecaFamily
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Rounded.ArrowForward,
                    contentDescription = null,
                    tint = Color(0xFF8B5CF6)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(institutionalNotices) { notice ->
                NoticeCard(
                    notice = notice,
                    isExpanded = selectedNotice == notice,
                    onExpandToggle = {
                        selectedNotice = if (selectedNotice == notice) null else notice
                    }
                )
            }
        }
    }
}

@Composable
fun NoticeCard(
    notice: InstitutionalNotice,
    isExpanded: Boolean,
    onExpandToggle: () -> Unit,
) {
    val cornerRadius = 24.dp
    val cardHeight = 220.dp  // Altura fija para el estado colapsado
    val expandedHeight = 280.dp  // Altura fija para el estado expandido

    val scale by animateFloatAsState(
        targetValue = if (isExpanded) 1.02f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    Surface(
        modifier = Modifier
            .width(280.dp)
            .height(if (isExpanded) expandedHeight else cardHeight)
            .scale(scale)
            .clip(RoundedCornerShape(cornerRadius))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = notice.color)
            ) { onExpandToggle() }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        shape = RoundedCornerShape(cornerRadius),
        color = Color(0xFF1A1A1A)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween  // Distribuye el espacio verticalmente
        ) {
            Column {
                // Header con iconos y fecha
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(
                                color = notice.color.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(14.dp)
                            )
                            .blur(radius = if (isExpanded) 0.dp else 0.5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            notice.icon,
                            contentDescription = null,
                            tint = notice.color,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            notice.type,
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = notice.color
                            ),
                            fontFamily = lexendDecaFamily
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                Icons.Rounded.DateRange,
                                contentDescription = null,
                                tint = Color(0xFF9CA3AF),
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                notice.date,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color(0xFF9CA3AF)
                                ),
                                fontFamily = lexendDecaFamily
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Título con animación de tamaño
                Text(
                    notice.title,
                    style = TextStyle(
                        fontSize = if (isExpanded) 18.sp else 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        lineHeight = 24.sp,
                        letterSpacing = (-0.5).sp
                    ),
                    fontFamily = lexendDecaFamily,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Descripción expandible
                Text(
                    notice.description,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFFD1D5DB),
                        lineHeight = 20.sp
                    ),
                    fontFamily = lexendDecaFamily,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Botones en la parte inferior
            if (isExpanded) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = notice.color,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        "Ver detalles",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        fontFamily = lexendDecaFamily
                    )
                }
            } else {
                TextButton(
                    onClick = onExpandToggle,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        "Expandir",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = notice.color
                        ),
                        fontFamily = lexendDecaFamily
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        Icons.Rounded.KeyboardArrowDown,
                        contentDescription = null,
                        tint = notice.color,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DailyClassesSection(
    currentDay: String = "Lun",
    currentTime: String = "18:30",  // Para pruebas, en producción usar hora actual
) {
    val classes = getModernScheduleForDay(currentDay)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Materias de Hoy",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    letterSpacing = (-0.5).sp
                ),
                fontFamily = lexendDecaFamily
            )

            Surface(
                shape = CircleShape,
                color = Color(0xFF34D399).copy(alpha = 0.1f),
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    text = "${classes.size} materias",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF34D399)
                    ),
                    fontFamily = lexendDecaFamily
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        classes.forEach { (time, classInfo) ->
            ClassCard(
                time = time,
                classInfo = classInfo,
                isCurrentClass = time == currentTime
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun ClassCard(
    time: String,
    classInfo: ClassInfo,
    isCurrentClass: Boolean,
) {
    val cornerRadius = 20.dp
    val backgroundColor = if (isCurrentClass) {
        Brush.linearGradient(colors = classInfo.gradientColors)
    } else {
        Brush.linearGradient(
            colors = listOf(
                Color(0xFF2A2A2A),
                Color(0xFF2A2A2A)
            )
        )
    }

    Surface(
        shape = RoundedCornerShape(cornerRadius),
        color = Color.Transparent,  // Surface transparente
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = classInfo.gradientColors.first())
            ) {}
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isCurrentClass) {
                            Color.White.copy(alpha = 0.15f)
                        } else {
                            classInfo.gradientColors.first().copy(alpha = 0.15f)
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            Icons.Rounded.Home,
                            contentDescription = null,
                            tint = if (isCurrentClass) Color.White else classInfo.gradientColors.first(),
                            modifier = Modifier
                                .padding(12.dp)
                                .size(24.dp)
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                classInfo.name,
                                style = TextStyle(
                                    fontSize = if (isCurrentClass) 15.sp else 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (isCurrentClass) Color.White else Color.White.copy(
                                        alpha = 0.9f
                                    )
                                ),
                                modifier = Modifier.weight(1f),
                                fontFamily = lexendDecaFamily
                            )

                            if (isCurrentClass) {
                                Surface(
                                    shape = RoundedCornerShape(100.dp),
                                    color = Color(0xFF34D399).copy(alpha = 0.2f)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(
                                            horizontal = 8.dp,
                                            vertical = 4.dp
                                        ),
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(6.dp)
                                                .background(Color(0xFF34D399), CircleShape)
                                        )
                                        Text(
                                            "En curso",
                                            style = TextStyle(
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = Color(0xFF34D399)
                                            ),
                                            fontFamily = lexendDecaFamily
                                        )
                                    }
                                }
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    Icons.Rounded.DateRange,
                                    contentDescription = null,
                                    tint = if (isCurrentClass) Color.White.copy(alpha = 0.7f) else Color(
                                        0xFF9CA3AF
                                    ),
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    "$time - ${calculateEndTime(time, classInfo.duration)}",
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = if (isCurrentClass) Color.White.copy(alpha = 0.7f) else Color(
                                            0xFF9CA3AF
                                        )
                                    ),
                                    fontFamily = lexendDecaFamily
                                )
                            }

                            Text(
                                "•",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = if (isCurrentClass) Color.White.copy(alpha = 0.7f) else Color(
                                        0xFF9CA3AF
                                    )
                                )
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    Icons.Rounded.LocationOn,
                                    contentDescription = null,
                                    tint = if (isCurrentClass) Color.White.copy(alpha = 0.7f) else Color(
                                        0xFF9CA3AF
                                    ),
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    classInfo.location,
                                    style = TextStyle(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = if (isCurrentClass) Color.White.copy(alpha = 0.7f) else Color(
                                            0xFF9CA3AF
                                        )
                                    ),
                                    fontFamily = lexendDecaFamily
                                )
                            }
                        }

                        Text(
                            classInfo.professor,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (isCurrentClass) Color.White.copy(alpha = 0.7f) else Color(
                                    0xFF9CA3AF
                                )
                            ),
                            fontFamily = lexendDecaFamily
                        )
                    }
                }

                if (!isCurrentClass) {
                    Icon(
                        Icons.Rounded.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color(0xFF6B7280),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

private fun calculateEndTime(startTime: String, durationMinutes: Int): String {
    val (hours, minutes) = startTime.split(":").map { it.toInt() }
    val totalMinutes = hours * 60 + minutes + durationMinutes
    val endHours = totalMinutes / 60
    val endMinutes = totalMinutes % 60
    return "%02d:%02d".format(endHours, endMinutes)
}

data class InstitutionalNotice(
    val type: String,
    val date: String,
    val title: String,
    val description: String,
    val color: Color,
    val icon: ImageVector,
)


val institutionalNotices = listOf(
    InstitutionalNotice(
        "Comunicado Oficial",
        "9 Dic 2024",
        "Suspensión de actividades académicas",
        "Debido a actividades institucionales programadas, se suspenden las clases el día viernes 13 de diciembre.",
        Color(0xFFF59E0B),
        Icons.Rounded.Done
    ),
    InstitutionalNotice(
        "Actualización",
        "8 Dic 2024",
        "Nuevo sistema de laboratorios",
        "Se ha implementado un nuevo sistema de reserva de laboratorios. Por favor revisa la guía de uso.",
        Color(0xFF3B82F6),
        Icons.Rounded.Face
    ),
    InstitutionalNotice(
        "Evento",
        "7 Dic 2024",
        "Feria de Tecnología 2024",
        "No te pierdas la feria tecnológica este 15 de diciembre. Grandes empresas estarán presentes.",
        Color(0xFF10B981),
        Icons.Rounded.DateRange
    )
)


@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentDateFormatted(): String {
    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM")
    return today.format(formatter).capitalize()
}

fun String.capitalize(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

@Composable
fun TopBar() {
    var isDarkMode by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF8B5CF6)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "CM",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    fontFamily = lexendDecaFamily
                )
            }

            Column {
                Text(
                    "Carlos Martinez",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    fontFamily = lexendDecaFamily
                )
                Text(
                    "Estudiante",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF9CA3AF)
                    ),
                    fontFamily = lexendDecaFamily
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = { isDarkMode = !isDarkMode },
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF2A2A2A))
            ) {
                Icon(
                    if (isDarkMode) Icons.Rounded.Notifications else Icons.Rounded.Notifications,
                    contentDescription = "Cambiar tema",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF2A2A2A))
            ) {
                Icon(
                    Icons.Rounded.Settings,
                    contentDescription = "Configuración",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun TabSection(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ModernTab(
            text = "Inicio",
            icon = Icons.Rounded.Home,
            isSelected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            modifier = Modifier.weight(1f)
        )
        ModernTab(
            text = "Horario",
            icon = Icons.Rounded.DateRange,
            isSelected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ModernTab(
    text: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .height(46.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color(0xFF8B5CF6) else Color(0xFF2A2A2A)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) Color.White else Color(0xFF9CA3AF),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSelected) Color.White else Color(0xFF9CA3AF)
                ),
                fontFamily = lexendDecaFamily
            )
        }
    }
}

data class ClassInfo(
    val name: String,
    val professor: String,
    val location: String,
    val duration: Int,
    val gradientColors: List<Color>,
    val iconTint: Color,
)

@Composable
fun ModernScheduleApp() {
    var selectedDay by remember { mutableStateOf("Lun") }
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1A1A1A),
            Color(0xFF2D2D2D)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 24.dp)
        ) {
            ModernHeader()
            AnimatedDaySelector(selectedDay) { selectedDay = it }
            ModernDailySchedule(selectedDay)
        }
    }
}

@Composable
fun ModernHeader() {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        Text(
            "Mi Horario",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                letterSpacing = (-1).sp
            ),
            fontFamily = lexendDecaFamily
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                Icons.Filled.Home,
                contentDescription = null,
                tint = Color(0xFF6B7280),
                modifier = Modifier.size(18.dp)
            )
            Text(
                "Carrera Sistemas Informaticos",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF6B7280)
                ),
                fontFamily = lexendDecaFamily
            )
        }
    }
}

@Composable
fun AnimatedDaySelector(selectedDay: String, onDaySelected: (String) -> Unit) {
    val days = listOf(
        "Lun" to "Lunes",
        "Mar" to "Martes",
        "Mié" to "Miércoles",
        "Jue" to "Jueves",
        "Vie" to "Viernes"
    )

    Column(
        modifier = Modifier.padding(vertical = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            days.forEach { (shortDay, fullDay) ->
                ModernDayButton(
                    shortDay = shortDay,
                    fullDay = fullDay,
                    isSelected = shortDay == selectedDay,
                    onClick = { onDaySelected(shortDay) }
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ModernDayButton(
    shortDay: String,
    fullDay: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = when (isSelected) {
                        true -> Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF8B5CF6),
                                Color(0xFF7C3AED)
                            )
                        )

                        false -> SolidColor(Color(0xFF2A2A2A))
                    }
                )
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        colors = if (isSelected) {
                            listOf(
                                Color.White.copy(alpha = 0.5f),
                                Color.White.copy(alpha = 0.2f)
                            )
                        } else {
                            listOf(
                                Color.White.copy(alpha = 0.1f),
                                Color.White.copy(alpha = 0.05f)
                            )
                        }
                    ),
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                shortDay,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) Color.White else Color(0xFF6B7280)
                ),
                fontFamily = lexendDecaFamily
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            fullDay,
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = if (isSelected) Color.White else Color(0xFF6B7280)
            ),
            fontFamily = lexendDecaFamily
        )
    }
}

@Composable
fun ModernDailySchedule(selectedDay: String) {
    val schedule = getModernScheduleForDay(selectedDay)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        schedule.forEach { (time, classInfo) ->
            ModernTimeSlot(time, classInfo)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ModernTimeSlot(time: String, classInfo: ClassInfo?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.End,

        ) {
            Text(
                time,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF6B7280)
                ),
                fontFamily = lexendDecaFamily
            )
        }

        if (classInfo != null) {
            ModernClassCard(classInfo)
        }
    }
}

@Composable
fun ModernClassCard(classInfo: ClassInfo) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        shape = RoundedCornerShape(24.dp),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(classInfo.gradientColors),
                    shape = RoundedCornerShape(24.dp)
                )
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        listOf(
                            Color.White.copy(alpha = 0.2f),
                            Color.White.copy(alpha = 0.1f)
                        )
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            classInfo.name,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            fontFamily = lexendDecaFamily
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            classInfo.professor,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White.copy(alpha = 0.7f)
                            ),
                            fontFamily = lexendDecaFamily
                        )
                    }

                    Surface(
                        shape = CircleShape,
                        color = classInfo.iconTint.copy(alpha = 0.2f)
                    ) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = null,
                            tint = classInfo.iconTint,
                            modifier = Modifier
                                .padding(12.dp)
                                .size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.Black.copy(alpha = 0.2f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.LocationOn,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.7f),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            classInfo.location,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White.copy(alpha = 0.7f)
                            ),
                            fontFamily = lexendDecaFamily
                        )
                    }
                }
            }
        }
    }
}

fun getModernScheduleForDay(day: String): Map<String, ClassInfo> {
    return when (day) {
        "Lun" -> mapOf(
            "18:30" to ClassInfo(
                "REDES DE COMPUTADORAS II",
                "VASQUEZ MARCELO",
                "LAB-3-MOVIL",
                70,
                listOf(Color(0xFF4F46E5), Color(0xFF4338CA)),
                Color(0xFF818CF8)
            ),
            "19:40" to ClassInfo(
                "PROGRAMACION III",
                "FRONTANILLA RODRIGO",
                "LAB-4-MOVIL",
                70,
                listOf(Color(0xFFEA580C), Color(0xFFC2410C)),
                Color(0xFFFB923C)
            ),
            "21:00" to ClassInfo(
                "DISEÑO Y PROGRAMACION WEB II",
                "CACERES PAVEL",
                "LAB-1-PB",
                70,
                listOf(Color(0xFF059669), Color(0xFF047857)),
                Color(0xFF34D399)
            )
        )

        "Mar" -> mapOf(
            "18:30" to ClassInfo(
                "GESTION DE SOFTWARE",
                "VERA RAUL",
                "LAB-4-MOVIL",
                70,
                listOf(Color(0xFFDB2777), Color(0xFFBE185D)),
                Color(0xFFF472B6)
            ),
            "19:40" to ClassInfo(
                "BASE DE DATOS II",
                "ALANEZ JOEL",
                "LAB-SECRETARIADO",
                70,
                listOf(Color(0xFF7C3AED), Color(0xFF6D28D9)),
                Color(0xFFA78BFA)
            ),
            "21:00" to ClassInfo(
                "TALLER DE MODALIDAD DE GRADUACION",
                "FLORES EDSON",
                "AULA 2 - 4",
                70,
                listOf(Color(0xFFDC2626), Color(0xFFB91C1C)),
                Color(0xFFFCA5A5)
            )
        )

        "Mié" -> mapOf(
            "18:30" to ClassInfo(
                "GESTION DE SOFTWARE",
                "VERA RAUL",
                "LAB-4-MOVIL",
                70,
                listOf(Color(0xFF0D9488), Color(0xFF0F766E)),
                Color(0xFF5EEAD4)
            ),
            "19:40" to ClassInfo(
                "ANALISIS Y DISEÑO DE SISTEMAS II",
                "ESCALERA DAVID",
                "LAB-4-MOVIL",
                70,
                listOf(Color(0xFFEA580C), Color(0xFFC2410C)),
                Color(0xFFFB923C)
            ),
            "21:00" to ClassInfo(
                "BASE DE DATOS II",
                "ALANEZ JOEL",
                "LAB-SISTEMAS",
                70,
                listOf(Color(0xFF4F46E5), Color(0xFF4338CA)),
                Color(0xFF818CF8)
            )
        )

        "Jue" -> mapOf(
            "18:30" to ClassInfo(
                "TALLER DE MODALIDAD DE GRADUACION",
                "FLORES EDSON",
                "AULA 1 - 7 (72)",
                70,
                listOf(Color(0xFFD946EF), Color(0xFFC026D3)),
                Color(0xFFF0ABFC)
            ),
            "19:40" to ClassInfo(
                "EMPRENDIMIENTO PRODUCTIVO",
                "CACERES PAVEL",
                "AULA 2 - 4",
                70,
                listOf(Color(0xFF0EA5E9), Color(0xFF0284C7)),
                Color(0xFF7DD3FC)
            ),
            "21:00" to ClassInfo(
                "ANALISIS Y DISEÑO DE SISTEMAS II",
                "ESCALERA DAVID",
                "AULA 2 - 4",
                70,
                listOf(Color(0xFF84CC16), Color(0xFF65A30D)),
                Color(0xFFBEF264)
            )
        )

        "Vie" -> mapOf(
            "18:30" to ClassInfo(
                "DISEÑO Y PROGRAMACION WEB II",
                "CACERES PAVEL",
                "LAB-2-2DO PISO",
                70,
                listOf(Color(0xFFF59E0B), Color(0xFFD97706)),
                Color(0xFFFBD38D)
            ),
            "19:40" to ClassInfo(
                "PROGRAMACION III",
                "FRONTANILLA RODRIGO",
                "LAB-3-MOVIL",
                70,
                listOf(Color(0xFF6366F1), Color(0xFF4F46E5)),
                Color(0xFFA5B4FC)
            ),
            "21:00" to ClassInfo(
                "REDES DE COMPUTADORAS II",
                "VASQUEZ MARCELO",
                "AULA 2 - 4",
                70,
                listOf(Color(0xFFEC4899), Color(0xFFDB2777)),
                Color(0xFFF9A8D4)
            )
        )

        else -> mapOf()
    }
}












