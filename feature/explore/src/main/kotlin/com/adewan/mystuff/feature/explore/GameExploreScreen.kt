package com.adewan.mystuff.feature.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.common.ux.CenteredLoadingIndicator
import com.adewan.mystuff.common.ux.PosterReelItem
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

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun GameExploreScreenWithData(modifier: Modifier, data: GameExploreViewState.Result) {
    Column(modifier = modifier.fillMaxSize()) {
        HorizontalPager(count = data.posterReelItems.size) {
            PosterReelItem(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp)
                    .clickable { },
                data = data.posterReelItems[it]
            )
        }
    }
}

@Preview
@Composable
fun PreviewGameExploreScreen() {
    GameExploreScreen()
}
