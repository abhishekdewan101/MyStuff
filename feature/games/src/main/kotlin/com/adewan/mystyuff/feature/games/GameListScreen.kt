@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.mystyuff.feature.games

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adewan.mystuff.common.ux.CenteredLoadingIndicator
import com.adewan.mystuff.core.models.games.posterUrl
import com.adewan.mystyuff.feature.games.navigation.GameListNavArgs
import org.koin.androidx.compose.getViewModel
import proto.Game

@Composable
fun GameListScreen(
    modifier: Modifier = Modifier,
    args: GameListNavArgs,
    viewModel: GameExploreViewModel = getViewModel()
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.getGamesListForQuery(args.query)
    }

    val viewState by viewModel.listViewState.collectAsState()

    Scaffold(modifier = modifier.fillMaxSize(), topBar = { GameListTitleBar(title = args.title) }) {
        when (viewState) {
            GameListViewState.Loading -> CenteredLoadingIndicator()
            is GameListViewState.Result -> FullGameList(
                modifier = Modifier.padding(it),
                data = (viewState as GameListViewState.Result).data
            )
        }
    }
}

@Composable
internal fun FullGameList(modifier: Modifier, data: List<Game>) {
    LazyVerticalGrid(
        modifier = modifier
            .padding(top = 10.dp)
            .padding(horizontal = 10.dp)
            .navigationBarsPadding()
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(data.size) {
            AsyncImage(
                model = data[it].posterUrl(),
                modifier = Modifier
                    .width(125.dp)
                    .height(175.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun GameListTitleBar(title: String) {
    CenterAlignedTopAppBar(title = {
        Text(
            title,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
    })
}
