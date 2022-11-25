@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.mystuff.feature.gameslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun GameListScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize(), topBar = { GameListTitleBar() }) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(it)
                .padding(top = 10.dp)
                .padding(horizontal = 10.dp)
                .navigationBarsPadding()
                .fillMaxSize(),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(102) {
                Box(
                    modifier = Modifier
                        .width(125.dp)
                        .height(175.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )
            }
        }
    }
}

@Composable
private fun GameListTitleBar() {
    CenterAlignedTopAppBar(title = {
        Text(
            "Coming Soon",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
    })
}
