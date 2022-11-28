package com.adewan.mystuff.feature.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

@Composable
fun DetailsView(id: String) {
    SideEffect {
        println("id --> $id")
    }
}
