package com.adewan.mystuff.common.utils

import android.graphics.Bitmap
import android.util.TypedValue
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap

@Composable
fun changeableColorBitmapResource(
    @DrawableRes id: Int,
    srcColor: Color,
    toColor: Color
): ImageBitmap {
    val context = LocalContext.current
    val res = context.resources
    val value = remember { TypedValue() }
    res.getValue(id, value, true)

    val bitmap = remember {
        context.getDrawable(id) ?: throw IllegalStateException("Local images cannot be null")
    }.toBitmap()

    // Source image size
    val width: Int = bitmap.width
    val height: Int = bitmap.height
    val pixels = IntArray(width * height)
    // get pixels
    bitmap.getPixels(pixels, 0, width, 0, 0, width, height)
    for (x in pixels.indices) {
        pixels[x] = if (pixels[x] == srcColor.toArgb()) toColor.toArgb() else pixels[x]
    }
    // create result bitmap output
    val result: Bitmap = Bitmap.createBitmap(width, height, bitmap.config)
    // set pixels
    result.setPixels(pixels, 0, width, 0, 0, width, height)
    return result.asImageBitmap()
}
