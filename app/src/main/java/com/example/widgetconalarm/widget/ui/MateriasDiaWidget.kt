package com.example.widgetconalarm.widget.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
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
import androidx.glance.layout.wrapContentSize
import androidx.glance.layout.wrapContentWidth
import com.example.widgetconalarm.R
import com.example.widgetconalarm.components.CustomGlanceText
import com.example.widgetconalarm.models.Horario
import com.example.widgetconalarm.utils.GlanceText2

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun EventsContent(plannings: List<Horario>) {

    val itemPadding = 10.dp

    val nombreDia = DateTimeFormatter.ofPattern("EEEE").format(LocalDate.now()).uppercase()


    Row(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            verticalAlignment = Alignment.CenterVertically,
            modifier = GlanceModifier
                .wrapContentWidth()
                .padding(start = 15.dp, top = 25.dp, end = 10.dp)
                .fillMaxHeight()
        ) {

            GlanceText2(
                text = nombreDia,
                color = Color.Black,
                font = R.font.gill_sans_medium,
                fontSize = 15.sp
            )
            GlanceText2(
                text = DateTimeFormatter.ofPattern("dd").format(LocalDate.now()) + " de " +
                        DateTimeFormatter.ofPattern("MMMM").format(LocalDate.now()).capitalize(),
                color = Color.Black,
                font = R.font.gill_sans,
                fontSize = 13.sp
            )
            Spacer(
                modifier = GlanceModifier.height(5.dp)
            )
            Image(
                provider = ImageProvider(resId = R.drawable.incoslogo),
                contentDescription = "",
                modifier = GlanceModifier.size(120.dp)
            )
        }



        Box(modifier = GlanceModifier.padding(vertical = 15.dp)) {
            Box(
                modifier = GlanceModifier
                    .fillMaxHeight()
                    .width(1.2.dp)
                    .background(Color.Black)
            ) {
            }
        }

        Column(
            modifier = GlanceModifier
                .fillMaxHeight()
                .padding(start = 7.dp)
                .fillMaxWidth()
        ) {
            if (plannings.isEmpty()) {
                Box(
                    modifier = GlanceModifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    GlanceText2(
                        modifier = GlanceModifier.wrapContentSize(),
                        text = "No hay clases hoy!",
                        color = Color.Black,
                        letterSpacing = 0.03f.sp,
                        font = R.font.tuesday_night_regular,
                        fontSize = 40.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = GlanceModifier,
                    horizontalAlignment = Alignment.Start
                ) {
                    item {
                        Spacer(modifier = GlanceModifier.height(3.dp))
                    }
                    items(plannings) { planning ->
                        Box(
                            modifier = GlanceModifier
                                .wrapContentHeight()
                                .padding(start = 5.dp, end = 10.dp, top = itemPadding)
                                .fillMaxSize(),

                            contentAlignment = Alignment.CenterStart,
                        ) {
                            Box(
                                modifier = GlanceModifier
                                    .wrapContentHeight()
                                    .cornerRadius(15.dp)
                                    .background(planning.highlight),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                val profesor = planning.profesor
                                val materia = planning.materia
                                val horaAula = "${planning.entrada} - ${planning.aula}"
                                Column(
                                    modifier = GlanceModifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp) // Reducido el padding
                                ) {
                                    CustomGlanceText(
                                        text = profesor,
                                        fontResource = R.font.gill_sans_light,
                                        fontSize = 23.sp,
                                        letterSpacing = 0.03f.sp,
                                        modifier = GlanceModifier.wrapContentHeight(),
                                    )

                                    CustomGlanceText(
                                        text = materia,
                                        fontResource = R.font.gill_sans_medium,
                                        fontSize = 26.sp,
                                        maxLines = 2,
                                        modifier = GlanceModifier.wrapContentHeight().padding(vertical = 5.dp),
                                    )

                                    CustomGlanceText(
                                        text = horaAula,
                                        fontResource = R.font.gill_sans,
                                        fontSize = 24.sp,
                                        letterSpacing = 0.03f.sp,
                                        modifier = GlanceModifier.wrapContentHeight(),
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Spacer(modifier = GlanceModifier.height(itemPadding))
                    }
                }
            }
        }
    }
}