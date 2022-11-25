package com.adewan.mystuff.feature.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.common.ux.CenteredLoadingIndicator
import com.adewan.mystuff.common.ux.ImageCarouselWithTitle
import com.adewan.mystuff.common.ux.ImageCarouselWithTitleData
import com.adewan.mystuff.common.ux.PosterGridWithTitle
import com.adewan.mystuff.common.ux.PosterGridWithTitleData
import com.adewan.mystuff.common.ux.PosterReelItem
import com.adewan.mystuff.core.models.games.posterUrl
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import org.koin.androidx.compose.getViewModel

@Composable
internal fun GameExploreScreen(
    modifier: Modifier = Modifier,
    viewModel: GameExploreViewModel = getViewModel(),
    navigateToGamesList: (String) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    when (viewState) {
        GameExploreViewState.Loading -> CenteredLoadingIndicator()
        is GameExploreViewState.Result -> GameExploreScreenWithData(
            modifier = modifier,
            data = viewState as GameExploreViewState.Result,
            navigateToGamesList = navigateToGamesList
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun GameExploreScreenWithData(
    modifier: Modifier,
    data: GameExploreViewState.Result,
    navigateToGamesList: (String) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            HorizontalPager(count = data.posterReelItems.size) {
                PosterReelItem(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 10.dp)
                        .clickable { },
                    data = data.posterReelItems[it]
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            val posterGridData = PosterGridWithTitleData(
                title = data.posterGrid1.title,
                urlIdPairs = data.posterGrid1.games.associate { it.posterUrl() to it.slug }
            )
            PosterGridWithTitle(
                modifier = Modifier.padding(horizontal = 10.dp),
                data = posterGridData,
                onSeeAllTap = { navigateToGamesList(data.posterGrid1.dataQuery) },
                onTap = {}
            )
        }

        item {
            val carouselWithTitleData = ImageCarouselWithTitleData(
                title = data.posterGrid2.title,
                images = data.posterGrid2.games.map { it.posterUrl() },
                identifier = data.posterGrid2.games.map { it.slug }
            )
            ImageCarouselWithTitle(
                modifier = Modifier.padding(horizontal = 10.dp),
                data = carouselWithTitleData,
                onTap = {},
                onSeeAllTap = { navigateToGamesList(data.posterGrid2.dataQuery) }
            )
        }

        item {
            val posterGridData = PosterGridWithTitleData(
                title = data.posterGrid3.title,
                urlIdPairs = data.posterGrid3.games.associate { it.posterUrl() to it.slug }
            )
            PosterGridWithTitle(
                modifier = Modifier.padding(horizontal = 10.dp),
                data = posterGridData,
                onSeeAllTap = { navigateToGamesList(data.posterGrid3.dataQuery) },
                onTap = {}
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview
@Composable
fun PreviewGameExploreScreen() {
    GameExploreScreen(navigateToGamesList = {})
}
