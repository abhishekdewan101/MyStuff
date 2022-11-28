package com.adewan.mystuff.feature.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adewan.mystuff.common.ux.CenteredLoadingIndicator
import com.adewan.mystuff.common.ux.ErrorView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.compose.getViewModel
import proto.Artwork
import proto.Game

@Composable
fun DetailsView(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = getViewModel(),
    id: String,
    navigateBack: () -> Unit
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.loadDetails(id)
    }

    val viewState by viewModel.viewState.collectAsState()
    when (viewState) {
        DetailsViewState.Loading -> CenteredLoadingIndicator()
        DetailsViewState.Error -> ErrorView(message = "Uh Oh! \n Something went wrong")
        is DetailsViewState.Result -> Details(
            game = (viewState as DetailsViewState.Result).data,
            navigateBack = navigateBack
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Details(game: Game, navigateBack: () -> Unit) {
    Scaffold(topBar = { DetailsTopBar(title = game.name, navigateBack = navigateBack) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FeaturedView(featured = game.artworksList.map { art -> art.imageUrl() })
            Text(
                text = game.summary,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun FeaturedView(featured: List<String>) {
    val pagerState = rememberPagerState()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(count = featured.size, state = pagerState) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { }
            ) {
                AsyncImage(
                    model = featured[it],
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentScale = ContentScale.Crop
                )
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
internal fun DetailsTopBar(title: String, navigateBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = "Back")
            }
        }
    )
}

private fun Artwork.imageUrl(): String =
    "https://images.igdb.com/igdb/image/upload/t_1080p/$imageId.jpg"
