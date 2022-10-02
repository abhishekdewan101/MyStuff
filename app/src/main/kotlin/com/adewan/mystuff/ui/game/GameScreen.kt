@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.mystuff.ui.game

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.adewan.mystuff.ui.composables.ThemedSystemUiContainer
import com.adewan.ux.common.SearchBar

@Composable
fun GameScreen() {
    ThemedSystemUiContainer {
        Scaffold(topBar = { SearchBar(onSearchExecuted = {}) }) {
        }
    }
}
