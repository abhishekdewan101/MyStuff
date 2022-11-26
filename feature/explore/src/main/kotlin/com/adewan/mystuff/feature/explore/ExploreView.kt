package com.adewan.mystuff.feature.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ExploreView(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = 10.dp)) {
        CenterAlignedTopAppBar(
            title = { Text(text = "Explore", fontWeight = FontWeight.Bold) }
        )
    }
}
