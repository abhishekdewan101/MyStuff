package com.adewan.mystuff.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun ExpandedImageViewer(url: String) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    ThemedContainer(backgroundColor = Color.Black) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale = maxOf(1f, minOf(scale * zoom, 3f))
                        val maxX = (size.width * (scale - 1)) / 2
                        val minX = -maxX
                        offsetX = maxOf(minX, minOf(maxX, offsetX + pan.x))
                        val maxY = (size.height * (scale - 1)) / 2
                        val minY = -maxY
                        offsetY = maxOf(minY, minOf(maxY, offsetY + pan.y))
                    }
                }
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        translationX = offsetX,
                        translationY = offsetY,
                        scaleX = scale,
                        scaleY = scale
                    ),
                model = url,
                contentDescription = "",
                contentScale = ContentScale.Inside
            )
        }
    }
}
