//
//data class ClassSchedule(
//    val name: String,
//    val professor: String,
//    val classroom: String,
//    val startHour: Int,
//    val startMinute: Int,
//    val endHour: Int,
//    val endMinute: Int,
//    val color: Color
//)
//
//@Composable
//fun WeeklySchedule() {
//    val scheduleByDay = mapOf(
//        "Lun" to listOf(
//            ClassSchedule("Programación III", "Dr. Juan Pérez", "LAB-1", 18, 30, 19, 40, Color(0xFF3B82F6)),
//            ClassSchedule("Base de Datos II", "Ing. Ana López", "LAB-3", 19, 40, 20, 50, Color(0xFFF59E0B)),
//            ClassSchedule("Redes", "Msc. Carlos Ruiz", "AULA-4", 21, 0, 22, 10, Color(0xFF10B981))
//        ),
//        "Mar" to listOf(
//            ClassSchedule("Diseño Web II", "Ing. María García", "LAB-2", 18, 30, 19, 40, Color(0xFFEC4899)),
//            ClassSchedule("Sistemas Operativos", "Dr. Roberto Sánchez", "LAB-4", 19, 40, 20, 50, Color(0xFF8B5CF6)),
//            ClassSchedule("Inteligencia Artificial", "Dra. Laura Mendoza", "AULA-2", 21, 0, 22, 10, Color(0xFFEF4444))
//        ),
//        "Mié" to listOf(
//            ClassSchedule("Desarrollo Móvil", "Ing. Pedro Díaz", "LAB-1", 18, 30, 19, 40, Color(0xFF14B8A6)),
//            ClassSchedule("Seguridad Informática", "Msc. Diana Torres", "LAB-3", 19, 40, 20, 50, Color(0xFFF97316)),
//            ClassSchedule("Machine Learning", "Dr. Andrés Silva", "AULA-1", 21, 0, 22, 10, Color(0xFF6366F1))
//        ),
//        "Jue" to listOf(
//            ClassSchedule("Arquitectura Software", "Dr. Miguel Ángel", "LAB-2", 18, 30, 19, 40, Color(0xFFD946EF)),
//            ClassSchedule("Cloud Computing", "Ing. Sofia Vargas", "LAB-4", 19, 40, 20, 50, Color(0xFF0EA5E9)),
//            ClassSchedule("Testing", "Msc. Jorge Morales", "AULA-3", 21, 0, 22, 10, Color(0xFF84CC16))
//        ),
//        "Vie" to listOf(
//            ClassSchedule("DevOps", "Ing. Luis Castro", "LAB-1", 18, 30, 19, 40, Color(0xFFEAB308)),
//            ClassSchedule("Blockchain", "Dra. Carmen Ruiz", "LAB-3", 19, 40, 20, 50, Color(0xFF6366F1)),
//            ClassSchedule("Data Science", "Dr. Fernando Mesa", "AULA-2", 21, 0, 22, 10, Color(0xFFEC4899))
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
//            "Horario Universitario",
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
//    val timeSlots = listOf("18:30", "19:40", "21:00")
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .horizontalScroll(rememberScrollState())
//    ) {
//        Row(
//            modifier = Modifier.padding(start = 60.dp),
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            days.forEach { day ->
//                DayHeader(day)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        timeSlots.forEach { time ->
//            TimeRow(time = time, scheduleByDay = scheduleByDay)
//            Spacer(modifier = Modifier.height(8.dp))
//        }
//    }
//}
//
//@Composable
//fun DayHeader(day: String) {
//    Surface(
//        modifier = Modifier.width(200.dp),
//        shape = RoundedCornerShape(12.dp),
//        color = Color(0xFFF1F5F9)
//    ) {
//        Box(
//            modifier = Modifier.padding(12.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                day,
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
//fun TimeRow(time: String, scheduleByDay: Map<String, List<ClassSchedule>>) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp),
//        horizontalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        Box(
//            modifier = Modifier.width(60.dp),
//            contentAlignment = Alignment.CenterStart
//        ) {
//            Text(
//                time,
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = Color(0xFF64748B)
//                ),
//                fontFamily = lexendDecaFamily
//            )
//        }
//
//        scheduleByDay.forEach { (_, classes) ->
//            val currentClass = classes.find {
//                "${it.startHour}:${it.startMinute.toString().padStart(2, '0')}" == time
//            }
//            if (currentClass != null) {
//                ClassCard(currentClass)
//            } else {
//                Spacer(modifier = Modifier.width(200.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun ClassCard(classSchedule: ClassSchedule) {
//    Surface(
//        modifier = Modifier.width(200.dp),
//        color = classSchedule.color.copy(alpha = 0.1f),
//        shape = RoundedCornerShape(12.dp)
//    ) {
//        Column(
//            modifier = Modifier.padding(12.dp)
//        ) {
//            Text(
//                classSchedule.name,
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = classSchedule.color
//                ),
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//                fontFamily = lexendDecaFamily
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Text(
//                classSchedule.professor,
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = Color(0xFF64748B)
//                ),
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//                fontFamily = lexendDecaFamily
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.spacedBy(4.dp)
//            ) {
//                Icon(
//                    Icons.Filled.Home,
//                    contentDescription = null,
//                    tint = Color(0xFF64748B),
//                    modifier = Modifier.size(14.dp)
//                )
//                Text(
//                    classSchedule.classroom,
//                    style = TextStyle(
//                        fontSize = 12.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = Color(0xFF64748B)
//                    ),
//                    fontFamily = lexendDecaFamily
//                )
//            }
//        }
//    }
//}