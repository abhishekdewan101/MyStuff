package com.adewan.mystuff.ui.home

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.ui.composables.TextFilterRow
import com.adewan.mystuff.ui.composables.TextFilterRowItem
import com.adewan.mystuff.ui.navigation.NavigationDirector

enum class DataFilter {
    Games,
    Movies,
    Tv
}

@Composable
fun HomeScreen(navigationDirector: NavigationDirector) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {
        TextFilterRow(
            modifier = Modifier.padding(top = 10.dp),
            filters = listOf(
                TextFilterRowItem(label = "Games", filter = DataFilter.Games),
                TextFilterRowItem(label = "Movies", filter = DataFilter.Movies),
                TextFilterRowItem(label = "TV Shows", filter = DataFilter.Tv)
            )
        ) {
        }
    }
}
