package com.adewan.mystuff.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.adewan.mystuff.ui.utils.NoRippleInteractionSource

@Composable
fun NavigationBottomBar(navHostController: NavHostController) {
    var currentSelectedIndex by rememberSaveable { mutableStateOf(0) }
    NavigationBar(modifier = Modifier.fillMaxWidth(), containerColor = Color.Transparent) {
        routes.forEachIndexed { index, dest ->
            NavigationBarItem(
                selected = currentSelectedIndex == index,
                interactionSource = NoRippleInteractionSource,
                onClick = {
                    currentSelectedIndex = index
                    navHostController.navigate(dest.route) {
                        restoreState = true
                        launchSingleTop = true
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
                icon = { Icon(dest.icon, contentDescription = dest.label) },
                label = { Text(dest.label) },
            )
        }
    }
}
