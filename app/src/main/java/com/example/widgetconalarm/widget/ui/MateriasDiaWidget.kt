package com.example.widgetconalarm.widget.ui


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
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
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentSize
import androidx.glance.layout.wrapContentWidth
import com.example.widgetconalarm.R
import com.example.widgetconalarm.models.Horario
import com.example.widgetconalarm.utils.GlanceText2
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@SuppressLint("NewApi")
@Composable
fun EventsContent(plannings: List<Horario>) {
    val context = LocalContext.current
    val itemPadding = 10.dp
    val itemHeight = 60.dp + itemPadding
    // primera letra del dia en mayuscula
    val nombreDia = DateTimeFormatter.ofPattern("EEEE").format(LocalDate.now()).uppercase()
    Row(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Column(
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
                //Fecha dd/mm - julio
                text = DateTimeFormatter.ofPattern("dd").format(LocalDate.now()) + " de " + DateTimeFormatter.ofPattern("MMMM").format(LocalDate.now()).capitalize(),
                color = Color.Black,
                font = R.font.gill_sans,
                fontSize = 13.sp
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
            modifier = GlanceModifier.fillMaxHeight()
                .padding(start = 7.dp)
        ) {
            if (plannings.isEmpty()) {
                Box(
                    modifier = GlanceModifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    GlanceText2(
                        modifier = GlanceModifier.wrapContentSize(),
                        text = "Nothing!",
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
                                .height(itemHeight)
                                .padding(start = 5.dp, end = 10.dp, top = itemPadding),
                            contentAlignment = Alignment.CenterStart,
                        ) {
                            Box(
                                modifier = GlanceModifier
                                    .fillMaxHeight()
                                    .cornerRadius(15.dp)
                                    .background(planning.highlight),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                val fullTitle = planning.profesor
                                val fullSubtitle = planning.materia
                                val fullDate = "${planning.entrada} - ${planning.aula}"
                                Column(modifier = GlanceModifier.padding(horizontal = 10.dp)) {
                                    GlanceText2(
                                        text = fullTitle,
                                        font = R.font.gill_sans_light,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.03f.sp
                                    )
                                    GlanceText2(
                                        text = fullSubtitle,
                                        font = R.font.gill_sans_medium,
                                        fontSize = 14.sp,
                                        letterSpacing = 0.03f.sp
                                    )
                                    GlanceText2(
                                        text = fullDate,
                                        font = R.font.gill_sans,
                                        fontSize = 13.sp,
                                        letterSpacing = 0.03f.sp
                                    )
//                                    Text(
//                                        text = fullTitle,
//                                        style = TextStyle(
//                                            fontSize = 17.sp,
//                                        )
//                                    )
//                                    Text(
//                                        text = fullSubtitle,
//                                        style = TextStyle(
//                                            fontSize = 15.sp,
//                                            fontWeight = FontWeight.Normal
//                                        )
//                                    )
//                                    Text(text = fullTitle)
//                                    Text(text = fullSubtitle)
//                                    GlanceText(
//                                        modifier = GlanceModifier
//                                            .padding(horizontal = 10.dp),
//                                        text = fullSubtitle,
//                                        color = Color.Black,
//                                        letterSpacing = 0.03f.sp,
//                                        font = R.font.gill_sans_light,
//                                        fontSize = 17.sp
//                                    )
//                                    GlanceText(
//                                        modifier = GlanceModifier.padding(horizontal = 10.dp),
//                                        text = fullTitle,
//                                        color = Color.Black,
//                                        letterSpacing = 0.03f.sp,
//                                        font = R.font.gill_sans_light,
//                                        fontSize = 17.sp
//                                    )

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}