package com.adewan.mystuff.ui.gamedetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.adewan.mystuff.ui.navigation.NavigationDirector

@Composable
fun GameDetailScreen(navigationDirector: NavigationDirector, identifier: String) {
    Text(text = "Game Details for $identifier")
}
