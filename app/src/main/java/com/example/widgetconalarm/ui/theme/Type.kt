package com.example.widgetconalarm.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.widgetconalarm.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )


    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val gillSansFamily = FontFamily(
    Font(R.font.gill_sans_light, FontWeight.Light),
    Font(R.font.gill_sans_medium, FontWeight.Medium),
    Font(R.font.gill_sans, FontWeight.Bold)
)

val lexendDecaFamily = FontFamily(
    Font(R.font.lexenddeca_extralight, FontWeight.ExtraLight),
    Font(R.font.lexenddeca_light, FontWeight.Light),
    Font(R.font.lexenddeca_regular, FontWeight.Normal),
    Font(R.font.lexenddeca_medium, FontWeight.Medium),
    Font(R.font.lexenddeca_semibold, FontWeight.SemiBold),
    Font(R.font.lexenddeca_bold, FontWeight.Bold),
    Font(R.font.lexenddeca_extrabold, FontWeight.ExtraBold),
    Font(R.font.lexenddeca_black, FontWeight.Black)
)