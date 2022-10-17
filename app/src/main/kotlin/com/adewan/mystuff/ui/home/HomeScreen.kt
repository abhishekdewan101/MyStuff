@file:OptIn(ExperimentalPagerApi::class)

package com.adewan.mystuff.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.ui.composables.CenteredLoadingIndicator
import com.adewan.mystuff.ui.composables.ImageCarouselWithTitle
import com.adewan.mystuff.ui.composables.ImageShowcase
import com.adewan.mystuff.ui.composables.TextFilterRow
import com.adewan.mystuff.ui.composables.TextFilterRowItem
import com.adewan.mystuff.ui.navigation.NavigationDirector
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navigationDirector: NavigationDirector, viewModel: HomeViewModel = getViewModel()) {
    val viewState by viewModel.viewState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        TextFilterRow(
            modifier = Modifier.padding(top = 10.dp),
            filters = listOf(
                TextFilterRowItem(label = "Games", filter = DataFilter.Games),
                TextFilterRowItem(label = "Movies", filter = DataFilter.Movies),
                TextFilterRowItem(label = "TV Shows", filter = DataFilter.Tv)
            ),
            onFilterSelected = viewModel::changeFilter
        )

        if (viewState == null) {
            CenteredLoadingIndicator()
        } else {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                ImageShowcase(
                    modifier = Modifier.padding(top = 15.dp),
                    items = viewState!!.showcase,
                    onImageTap = {
                        when (viewModel.currentFilter.value) {
                            DataFilter.Games -> navigationDirector.navigateToGameDetails(viewState!!.showcase[it].identifier)
                            else -> {}
                        }
                    }
                )
                ImageCarouselWithTitle(
                    modifier = Modifier.padding(top = 15.dp),
                    data = viewState!!.topRated,
                    onViewMore = {}
                )
                ImageCarouselWithTitle(
                    modifier = Modifier.padding(top = 15.dp),
                    data = viewState!!.comingSoon,
                    onViewMore = {}
                )
                ImageCarouselWithTitle(
                    modifier = Modifier.padding(top = 15.dp),
                    data = viewState!!.recentReleased,
                    onViewMore = {}
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
