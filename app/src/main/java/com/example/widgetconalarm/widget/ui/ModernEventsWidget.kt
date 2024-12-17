import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentHeight
import com.example.widgetconalarm.R
import com.example.widgetconalarm.components.CustomGlanceText

import com.example.widgetconalarm.models.Horario
import com.example.widgetconalarm.utils.GlanceText2
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ModernEventsWidget(plannings: List<Horario>) {
    val nombreDia = DateTimeFormatter.ofPattern("EEEE").format(LocalDate.now()).uppercase()
    val backgroundColor = Color(0xFFF8FAFD)

    Row(
        modifier = GlanceModifier
            .fillMaxSize()
            .cornerRadius(28.dp)
            .background(backgroundColor)
            .padding(12.dp)
    ) {
        // Left Column - Date and Logo
        Column(
            verticalAlignment = Alignment.Top,
            modifier = GlanceModifier
                .fillMaxHeight()
                .padding(start = 16.dp, top = 16.dp)
        ) {
            GlanceText2(
                text = nombreDia,
                color = Color(0xFF1E293B),
                font = R.font.lexenddeca_bold,
                fontSize = 18.sp
            )

            GlanceText2(
                text = DateTimeFormatter.ofPattern("dd").format(LocalDate.now()),
                color = Color(0xFF3B82F6),
                font = R.font.lexenddeca_extrabold,
                fontSize = 32.sp
            )

            GlanceText2(
                text = DateTimeFormatter.ofPattern("MMMM").format(LocalDate.now()).capitalize(),
                color = Color(0xFF64748B),
                font = R.font.lexenddeca_medium,
                fontSize = 14.sp
            )

            Spacer(modifier = GlanceModifier.height(12.dp))

            Image(
                provider = ImageProvider(resId = R.drawable.incoslogo),
                contentDescription = "",
                modifier = GlanceModifier.size(80.dp)
            )
        }

        Spacer(modifier = GlanceModifier.width(16.dp))

        // Right Column - Class Schedule
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        ) {
            if (plannings.isEmpty()) {
                Box(
                    modifier = GlanceModifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    GlanceText2(
                        text = "No hay clases hoy",
                        color = Color(0xFF64748B),
                        font = R.font.lexenddeca_medium,
                        fontSize = 20.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = GlanceModifier.fillMaxSize()
                ) {
                    items(plannings) { planning ->
                        ClassCard(planning)
                        Spacer(modifier = GlanceModifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ClassCard(planning: Horario) {
    Box(
        modifier = GlanceModifier
            .fillMaxWidth()
            .wrapContentHeight()
            .cornerRadius(16.dp)
            .background(planning.highlight.copy(alpha = 0.9f))
            .padding(12.dp)
    ) {
        Column {
            CustomGlanceText(
                text = planning.materia,
                fontResource = R.font.lexenddeca_bold,
                fontSize = 16.sp,
                maxLines = 1,
                modifier = GlanceModifier.padding(bottom = 4.dp)
            )

            CustomGlanceText(
                text = planning.profesor,
                fontResource = R.font.lexenddeca_medium,
                fontSize = 14.sp,
                modifier = GlanceModifier.padding(bottom = 4.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = GlanceModifier.fillMaxWidth()
            ) {
                Image(
                    provider = ImageProvider(R.drawable.incoslogo),
                    contentDescription = null,
                    modifier = GlanceModifier.size(14.dp)
                )

                Spacer(modifier = GlanceModifier.width(4.dp))

                CustomGlanceText(
                    text = planning.entrada,
                    fontResource = R.font.lexenddeca_regular,
                    fontSize = 14.sp
                )

                Spacer(modifier = GlanceModifier.width(8.dp))

                Image(
                    provider = ImageProvider(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = GlanceModifier.size(14.dp)
                )

                Spacer(modifier = GlanceModifier.width(4.dp))

                CustomGlanceText(
                    text = planning.aula,
                    fontResource = R.font.lexenddeca_regular,
                    fontSize = 14.sp
                )
            }
        }
    }
}