package com.adewan.mystuff.feature.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.AsyncImage
import com.adewan.mystuff.common.ux.CenteredLoadingIndicator
import com.adewan.mystuff.common.ux.RatingBar
import com.adewan.mystuff.core.models.navigation.ExpandedViewArgs
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.compose.getViewModel

@Composable
fun ExploreView(
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = getViewModel(),
    navigateToExpandedView: (ExpandedViewArgs) -> Unit,
    navigateToDetailView: (String) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        CenterAlignedTopAppBar(
            title = { Text(text = "Explore", fontWeight = FontWeight.Bold) }
        )
        when (viewState) {
            ExploreViewState.Loading -> CenteredLoadingIndicator()
            ExploreViewState.Error -> ErrorView()
            is ExploreViewState.Results -> ExploreResults(
                results = (viewState as ExploreViewState.Results),
                navigateToExpandedView = navigateToExpandedView,
                navigateToDetailView = navigateToDetailView
            )
        }
    }
}

@Composable
internal fun ErrorView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_illus),
            contentDescription = "Library is empty",
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = "Uh Oh! \n Something went wrong",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}

@Composable
internal fun ExploreResults(
    results: ExploreViewState.Results,
    navigateToExpandedView: (ExpandedViewArgs) -> Unit,
    navigateToDetailView: (String) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        FeaturedView(featured = results.featurePosters, navigateToDetailView = navigateToDetailView)
        PosterGrid(
            gridGames = results.grid1,
            navigateToExpandedView = navigateToExpandedView,
            navigateToDetailView = navigateToDetailView
        )
        PosterCarousel(
            carouselItems = results.grid2,
            navigateToExpandedView = navigateToExpandedView,
            navigateToDetailView = navigateToDetailView
        )
        PosterGrid(
            gridGames = results.grid3,
            navigateToExpandedView = navigateToExpandedView,
            navigateToDetailView = navigateToDetailView
        )
        PosterCarousel(
            carouselItems = results.grid4,
            navigateToExpandedView = navigateToExpandedView,
            navigateToDetailView = navigateToDetailView
        )
    }
}

@Composable
internal fun PosterCarousel(
    modifier: Modifier = Modifier,
    carouselItems: PosterViewItem,
    navigateToExpandedView: (ExpandedViewArgs) -> Unit,
    navigateToDetailView: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = carouselItems.first,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            TextButton(onClick = {
                navigateToExpandedView(
                    ExpandedViewArgs(
                        title = carouselItems.first,
                        query = carouselItems.third.copy(limit = 100).buildQuery()
                    )
                )
            }) {
                Text(text = "See all")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(carouselItems.second) {
                AsyncImage(
                    model = it.poster,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(125.dp)
                        .height(175.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .clickable { navigateToDetailView(it.slug) }
                )
            }
        }
    }
}

@Composable
internal fun PosterGrid(
    modifier: Modifier = Modifier,
    gridGames: PosterViewItem,
    navigateToExpandedView: (ExpandedViewArgs) -> Unit,
    navigateToDetailView: (String) -> Unit
) {
    BoxWithConstraints {
        val width = maxWidth / 3
        Column(modifier = modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = gridGames.first,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                TextButton(onClick = {
                    navigateToExpandedView(
                        ExpandedViewArgs(
                            title = gridGames.first,
                            query = gridGames.third.copy(limit = 100).buildQuery()
                        )
                    )
                }) {
                    Text(text = "See all")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                gridGames.second.chunked(3).forEach { gameList ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier.padding(top = 5.dp)
                    ) {
                        gameList.forEach {
                            AsyncImage(
                                model = it.poster,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(width)
                                    .height(height = max(width * 0.67f, 175.dp))
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(MaterialTheme.colorScheme.secondaryContainer)
                                    .clickable { navigateToDetailView(it.slug) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun FeaturedView(
    featured: List<FeaturedPosterItem>,
    navigateToDetailView: (String) -> Unit
) {
    val pagerState = rememberPagerState()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(count = featured.size, state = pagerState) {
            val game = featured[it]
            Card(
                modifier = Modifier.padding(horizontal = 10.dp),
                onClick = { navigateToDetailView(game.slug) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    AsyncImage(
                        model = game.poster,
                        contentDescription = game.name,
                        modifier = Modifier
                            .width(100.dp)
                            .height(150.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.secondaryContainer),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier.padding(start = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = game.name,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        RatingBar(
                            rating = game.rating,
                            maxRating = game.totalRating,
                            iconTint = MaterialTheme.colorScheme.onSecondaryContainer,
                            iconSize = 24.dp
                        )
                        Text(
                            text = game.genreString,
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}
