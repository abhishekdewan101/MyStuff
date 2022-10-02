package com.adewan.ux.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ThemedContainer(
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit
) {
    val uiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    SideEffect {
        uiController.setSystemBarsColor(color = backgroundColor, darkIcons = useDarkIcons)
    }
    content()
}
