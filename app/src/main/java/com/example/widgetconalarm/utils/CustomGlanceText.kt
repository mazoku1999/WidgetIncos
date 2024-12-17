package com.example.widgetconalarm.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.text.StaticLayout
import android.text.Layout
import android.util.TypedValue
import androidx.annotation.FontRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.text.TextStyle

@Composable
fun CustomGlanceText(
    text: String,
    modifier: GlanceModifier = GlanceModifier,
    color: Color = Color.Black,
    fontSize: TextUnit = 14.sp,
    @FontRes fontResource: Int? = null,
    letterSpacing: TextUnit = 0.sp,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: Paint.Align = Paint.Align.LEFT,
    style: TextStyle? = null
) {
    val context = LocalContext.current

    Image(
        modifier = modifier,
        provider = ImageProvider(
            createTextBitmap(
                context = context,
                text = text,
                fontSize = fontSize,
                color = color,
                fontResource = fontResource,
                letterSpacing = letterSpacing.value,
                maxLines = maxLines,
                textAlign = textAlign
            )
        ),
        contentDescription = text
    )
}

private fun createTextBitmap(
    context: Context,
    text: String,
    fontSize: TextUnit,
    color: Color,
    fontResource: Int?,
    letterSpacing: Float,
    maxLines: Int,
    textAlign: Paint.Align
): Bitmap {
    val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        this.textSize = spToPx(fontSize.value, context)
        this.color = color.toArgb()
        this.textAlign = textAlign
        this.letterSpacing = letterSpacing

        if (fontResource != null) {
            this.typeface = ResourcesCompat.getFont(context, fontResource)
        }
    }

    val displayWidth = context.resources.displayMetrics.widthPixels
    val maxWidth = (displayWidth * 0.9).toInt()

    val staticLayout = StaticLayout.Builder
        .obtain(text, 0, text.length, paint, maxWidth)
        .setAlignment(when (textAlign) {
            Paint.Align.CENTER -> Layout.Alignment.ALIGN_CENTER
            Paint.Align.RIGHT -> Layout.Alignment.ALIGN_OPPOSITE
            else -> Layout.Alignment.ALIGN_NORMAL
        })
        .setMaxLines(maxLines)
        .setLineSpacing(0f, 1f)
        .setIncludePad(false)
        .setEllipsize(android.text.TextUtils.TruncateAt.END)
        .build()

    val height = staticLayout.height
    val heightPerLine = height / staticLayout.lineCount.toFloat()
    val finalHeight = (heightPerLine * minOf(maxLines, staticLayout.lineCount)).toInt()

    return Bitmap.createBitmap(maxWidth, finalHeight, Bitmap.Config.ARGB_8888).apply {
        val canvas = Canvas(this)
        staticLayout.draw(canvas)
    }
}

private fun spToPx(sp: Float, context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp,
        context.resources.displayMetrics
    )
}