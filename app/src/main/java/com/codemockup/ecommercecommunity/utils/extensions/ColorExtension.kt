package com.codemockup.ecommercecommunity.utils.extensions

import androidx.compose.ui.graphics.Color

fun Color.toInt(): Int {
    val alpha = (this.alpha * 255).toInt()
    val red = (this.red * 255).toInt()
    val green = (this.green * 255).toInt()
    val blue = (this.blue * 255).toInt()
    return (alpha shl 24) or (red shl 16) or (green shl 8) or blue
}