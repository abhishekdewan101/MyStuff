@file:OptIn(ExperimentalPagerApi::class)

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
import com.adewan.mystuff.ui.composables.ImageShowcase
import com.adewan.mystuff.ui.composables.ImageShowcaseItem
import com.adewan.mystuff.ui.composables.TextFilterRow
import com.adewan.mystuff.ui.composables.TextFilterRowItem
import com.adewan.mystuff.ui.navigation.NavigationDirector
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

enum class DataFilter {
    Games,
    Movies,
    Tv
}

@Composable
fun HomeScreen(navigationDirector: NavigationDirector) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(initialPage = 1)
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
            ),
            onFilterSelected = {
            }
        )
        ImageShowcase(
            modifier = Modifier.padding(top = 15.dp),
            items = listOf(
                ImageShowcaseItem(url = "https://picsum.photos/id/238/200/250", label = "Building"),
                ImageShowcaseItem(url = "https://picsum.photos/id/237/200/250", label = "Puppy"),
                ImageShowcaseItem(url = "https://picsum.photos/id/239/200/250", label = "Flower"),
                ImageShowcaseItem(
                    url = "https://picsum.photos/id/236/200/250",
                    label = "House"
                )
            )
        )
    }
}
