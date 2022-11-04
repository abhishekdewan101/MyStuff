package com.adewan.mystuff.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp

@Composable
fun GradientScrimContainer(
    modifier: Modifier = Modifier,
    backgroundContent: @Composable () -> Unit,
    foregroundContent: @Composable () -> Unit,
    scrimHeight: Dp,
    scrimGradient: Brush,
) {
    Box(modifier = modifier) {
        backgroundContent()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(scrimHeight)
                .align(Alignment.BottomCenter)
                .background(brush = scrimGradient),
        ) {
            foregroundContent()
        }
    }
}
