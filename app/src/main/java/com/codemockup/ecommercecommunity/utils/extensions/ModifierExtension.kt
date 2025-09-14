package com.codemockup.ecommercecommunity.utils.extensions

import android.graphics.BlurMaskFilter
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

fun Modifier.shadowCustom(
    color: Color = Color.Black,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 4.dp,
    shape: Shape = RectangleShape,
) = composed {
    require(blurRadius > 0.dp) { "Blur radius must be greater than 0" }

    val paint: Paint = remember { Paint() }
    val blurRadiusPx = blurRadius.toPx(LocalDensity.current)
    val offsetXpx = offsetX.toPx(LocalDensity.current)
    val offsetYpx = offsetY.toPx(LocalDensity.current)

    val maskFilter = remember {
        BlurMaskFilter(blurRadiusPx, BlurMaskFilter.Blur.NORMAL)
    }

    drawBehind {
        drawIntoCanvas { canvas ->
            val frameworkPaint = paint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter = maskFilter
            }
            frameworkPaint.color = color.toArgb()

            val outline = shape.createOutline(size, LayoutDirection.Ltr, this)

            // Apply the offset by translating the canvas
            canvas.save()
            canvas.translate(offsetXpx, offsetYpx)
            canvas.drawOutline(
                outline = outline,
                paint = paint
            )
            canvas.restore()
        }
    }
}

private fun Dp.toPx(density: Density): Float = with(density) { toPx() }