package com.adewan.mystuff.feature.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.common.ux.CenteredLoadingIndicator
import com.adewan.mystuff.common.ux.PosterGridWithTitle
import com.adewan.mystuff.common.ux.PosterGridWithTitleData
import com.adewan.mystuff.common.ux.PosterReelItem
import com.adewan.mystuff.core.models.games.posterUrl
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import org.koin.androidx.compose.get

@Composable
internal fun GameExploreScreen(
    modifier: Modifier = Modifier,
    viewModel: GameExploreViewModel = get()
) {
    val viewState by viewModel.viewState.collectAsState()

    when (viewState) {
        GameExploreViewState.Loading -> CenteredLoadingIndicator()
        is GameExploreViewState.Result -> GameExploreScreenWithData(
            modifier = modifier,
            data = viewState as GameExploreViewState.Result
        )
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun GameExploreScreenWithData(modifier: Modifier, data: GameExploreViewState.Result) {
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
                urlIdPairs = data.posterGrid1.games.map { it.posterUrl() to it.slug }.toMap()
            )
            PosterGridWithTitle(
                modifier = Modifier.padding(horizontal = 10.dp),
                data = posterGridData,
                onSeeAllTap = { },
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
    GameExploreScreen()
}
