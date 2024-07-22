package com.example.widgetconalarm.widget.ui


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import com.example.widgetconalarm.R
import com.example.widgetconalarm.utils.GlanceText
import com.example.widgetconalarm.utils.GlanceText2
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@SuppressLint("NewApi")
@Composable
fun AndreaDiaWidget() {
    // primera letra del dia en mayuscula
    val nombreDia = DateTimeFormatter.ofPattern("EEEE").format(LocalDate.now()).uppercase()
    val nombreMes = DateTimeFormatter.ofPattern("dd")
        .format(LocalDate.now()) + " de " + DateTimeFormatter.ofPattern("MMMM")
        .format(LocalDate.now()).capitalize()
    val tamWidth = LocalSize.current.width
    val tamHeight = LocalSize.current.height
    val context = LocalContext.current
    Toast.makeText(context, "Ancho: ${tamWidth}", Toast.LENGTH_SHORT).show()
    Toast.makeText(context, "Alto: ${tamHeight}", Toast.LENGTH_SHORT).show()
    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.TopCenter
    ) {
//        Text(text = "Holaaa")
        Image(
            provider = ImageProvider(
                R.drawable.img
            ),
            contentDescription = "",
            modifier = GlanceModifier
                .size(tamWidth * 0.9f)
                .padding(start = 10.dp, end = 10.dp, bottom = tamHeight * 0.9f),
        )
        Column(
            modifier = GlanceModifier.padding(
                top = tamHeight * 0.6999f,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
        ) {

            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
//                    .height(100.dp)
                    .cornerRadius(15.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = GlanceModifier.height(tamHeight * 1.2f).padding(start = 10.dp, end = 10.dp), contentAlignment = Alignment.TopCenter) {
                    GlanceText2(
                        text = "DISEÑO Y PROGRAMACION WEB II",
                        font = R.font.gill_sans,
                        fontSize = 15.sp
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalAlignment = Alignment.CenterVertically
                        //fin
                    ) {
                        Column(horizontalAlignment = Alignment.End) {
                            GlanceText(
                                text = "AULA",
                                font = R.font.lexenddeca_regular,
                                fontSize = 11.sp,
                                letterSpacing = 0.3.sp,
                                color = Color(color = 0xFF3E2723)
                            )
                            GlanceText(
                                text = "2A",
                                font = R.font.lexenddeca_extrabold,
                                fontSize = 20.sp,
                                color = Color(color = 0xFF3E2723)
                            )
                            GlanceText(
                                text = "Flores Edson",
                                font = R.font.lexenddeca_bold,
                                fontSize = 12.sp,
                                color = Color.Red,
                                letterSpacing = 0.sp,
                            )
                        }
                        Box(
                            modifier = GlanceModifier.width(tamWidth * 0.4f)
                                .padding(horizontal = 10.dp)
                        ) {
                            Image(
                                provider = ImageProvider(
                                    resId = R.drawable.img,
                                ),
                                contentDescription = "gaa",

                                )
                        }
                        Column(horizontalAlignment = Alignment.Start) {
                            GlanceText(
                                text = "FECHA",
                                font = R.font.lexenddeca_regular,
                                fontSize = 11.sp,
                                letterSpacing = 0.3.sp
                            )
                            GlanceText(
                                text = nombreDia,
                                font = R.font.lexenddeca_bold,
                                fontSize = 17.sp,
                            )
                            GlanceText(
                                text = nombreMes,
                                font = R.font.lexenddeca_regular,
                                fontSize = 12.sp,

                                )
                        }
                    }
                }
            }
        }
    }
}


//@SuppressLint("NewApi")
//@Composable
//fun BowAndCard(modifier: Modifier = Modifier) {
//    androidx.compose.foundation.layout.Box(
//        modifier = modifier
//            .fillMaxWidth()
//            .height(500.dp)
//            .background(Color.Red)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.hk),
//            contentDescription = "Pink Bow",
//            modifier = Modifier
//                .fillMaxWidth()
////                .size(90.dp)
//                .align(androidx.compose.ui.Alignment.TopEnd), // Ajusta la alineación según sea necesario
//            contentScale = ContentScale.FillHeight
//        )
//        androidx.compose.foundation.layout.Column(
//            modifier = Modifier.padding(
//                top = 140.dp,
//                start = 16.dp,
//                end = 16.dp
//            )
//        ) {
//
////            Spacer(modifier = Modifier.height(16.dp))
//            Card(
//
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(130.dp),
//                shape = RoundedCornerShape(16.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.White
//                )
//            ) {
//                androidx.compose.foundation.layout.Row(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Absolute.Center
//                ) {
//                    androidx.compose.foundation.layout.Box(modifier = Modifier.width(90.dp)) {
//                        androidx.compose.foundation.layout.Column {
//                            androidx.compose.material3.Text(
//                                // cafe oscuro suave
//                                color = Color(color = 0xFF3E2723),
//                                text = "AULA",
//                                fontFamily = gillFamily,
//                                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
//                                fontSize = 11.sp,
//                                letterSpacing = 3.sp,
//                                style = androidx.compose.ui.text.TextStyle(
//
//                                )
//                            )
//                            androidx.compose.material3.Text(
//                                //Cafe oscuro
//                                color = Color(color = 0xFF3E2723),
//                                text = "2A",
//                                fontFamily = FontFamily.SansSerif,
//                                fontSize = 20.sp,
//                                fontWeight = androidx.compose.ui.text.font.FontWeight.Black,
//                                letterSpacing = 0.sp,
//                            )
//                            androidx.compose.material3.Text(
//
//                                text = "DISEÑO Y PROGRAMACION WEB II",
//                                style = androidx.compose.ui.text.TextStyle(
//                                    fontFamily = gillFamily,
//                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
//                                    fontSize = 10.sp,
//                                    color = Color(color = 0xFF3E2723),
////                                    lineHeight = 0.sp,
//                                ),
////                                overflow = TextOverflow.Ellipsis,
//                            )
//                            androidx.compose.material3.Text(
//                                style = androidx.compose.ui.text.TextStyle(
//                                    fontFamily = gillFamily,
//                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
//                                    fontSize = 12.sp,
//                                    color = Color.Red,
//                                ),
//                                text = "Flores Edson",
//                            )
//                        }
//                    }
//                    // mostrar xml en imagen
//                    Image(
//                        painter = painterResource(id = R.drawable.hk),
//                        contentDescription = "More",
//                        modifier = Modifier
//                            .size(104.dp)
//                            .align(androidx.compose.ui.Alignment.CenterVertically)
//                    )
////                    Text(text = "0")
//                    androidx.compose.foundation.layout.Box(
//                        modifier = Modifier.width(90.dp).padding(start = 14.dp),
//                    ) {
//                        androidx.compose.foundation.layout.Column {
//                            androidx.compose.material3.Text(
//                                // cafe oscuro suave
//                                color = Color(color = 0xFF3E2723),
//                                text = "FECHA",
//                                fontFamily = gillFamily,
//                                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
//                                fontSize = 11.sp,
//                                letterSpacing = 3.sp,
//                                style = androidx.compose.ui.text.TextStyle(
//
//                                )
//                            )
//                            androidx.compose.material3.Text(
//                                //Cafe oscuro
//
//                                text = DateTimeFormatter.ofPattern("EEEE").format(
//                                    LocalDate.now()
//                                ).uppercase(),
//                                style = androidx.compose.ui.text.TextStyle(
//                                    color = Color(color = 0xFF3E2723),
//                                    fontFamily = FontFamily.SansSerif,
//                                    fontSize = 17.sp,
//                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Black,
//                                ),
//                            )
//                            androidx.compose.material3.Text(
//
//                                text = DateTimeFormatter.ofPattern("dd")
//                                    .format(LocalDate.now()) + " de " + DateTimeFormatter.ofPattern(
//                                    "MMMM"
//                                ).format(LocalDate.now()).capitalize(),
//                                style = androidx.compose.ui.text.TextStyle(
//                                    fontFamily = gillFamily,
//                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
//                                    fontSize = 15.sp,
//                                    color = Color(color = 0xFF3E2723),
////                                    lineHeight = 0.sp,
//                                ),
//
//                            )
//
//                        }
//                    }
//                }
//            }
//        }
//    }
//}