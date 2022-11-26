package com.adewan.mystuff.feature.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.adewan.mystuff.common.ux.RatingBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import org.koin.androidx.compose.getViewModel

@Composable
fun ExploreView(modifier: Modifier = Modifier, viewModel: ExploreViewModel = getViewModel()) {
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
            is ExploreViewState.Results -> ExploreResults(results = (viewState as ExploreViewState.Results))
        }
    }
}

@Composable
internal fun ExploreResults(results: ExploreViewState.Results) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        FeaturedView(featured = results.featuredGames)
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun FeaturedView(featured: List<FeaturedGames>) {
    HorizontalPager(count = featured.size) {
        val game = featured[it]
        Card(modifier = Modifier.padding(horizontal = 10.dp), onClick = {}) {
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
}
