package com.adewan.mystuff.feature.details

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Splitscreen
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adewan.mystuff.common.ux.CenteredLoadingIndicator
import com.adewan.mystuff.common.ux.ErrorView
import com.adewan.mystuff.common.ux.RatingBar
import com.adewan.mystuff.common.ux.VideoPreview
import com.adewan.mystuff.core.models.games.buildYoutubeScreenshotUrl
import com.adewan.mystuff.core.models.games.buildYoutubeUrl
import com.adewan.mystuff.core.models.games.imageUrl
import com.adewan.mystuff.core.models.games.posterUrl
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.compose.getViewModel
import proto.Game
import proto.GameMode
import proto.GameVideo
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun DetailsView(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = getViewModel(),
    id: String,
    navigateBack: () -> Unit,
    navigateToDetailView: (String) -> Unit
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
            navigateBack = navigateBack,
            navigateToDetailView = navigateToDetailView
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Details(game: Game, navigateBack: () -> Unit, navigateToDetailView: (String) -> Unit) {
    Scaffold(topBar = { DetailsTopBar(title = game.name, navigateBack = navigateBack) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            FeaturedView(featured = (game.artworksList.map { art -> art.imageUrl() } + game.screenshotsList.map { screenshot -> screenshot.imageUrl() }))
            MetadataBlock(game = game)
            ExpandableSummary(summary = game.summary)
            Trailers(trailers = game.videosList)
            SimilarGames(games = game.similarGamesList, navigateToDetailView = navigateToDetailView)
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun SimilarGames(games: List<Game>, navigateToDetailView: (String) -> Unit) {
    val pagerState = rememberPagerState()
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = "Recommendations",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        HorizontalPager(count = games.size, state = pagerState) {
            val game = games[it]
            Card(
                modifier = Modifier.padding(horizontal = 5.dp),
                onClick = { navigateToDetailView(game.slug) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    AsyncImage(
                        model = game.posterUrl(),
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
                            rating = game.totalRating,
                            maxRating = 100.0,
                            iconTint = MaterialTheme.colorScheme.onSecondaryContainer,
                            iconSize = 24.dp
                        )
                        Text(
                            text = game.summary,
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun Trailers(trailers: List<GameVideo>) {
    val context = LocalContext.current
    val pagerState = rememberPagerState()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(count = trailers.size, state = pagerState) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    context.startActivity(
                        Intent(
                            ACTION_VIEW,
                            Uri.parse(trailers[it].buildYoutubeUrl())
                        )
                    )
                },
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                VideoPreview(
                    previewImage = {
                        AsyncImage(
                            model = trailers[it].buildYoutubeScreenshotUrl(),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            contentScale = ContentScale.Crop
                        )
                    },
                    title = trailers[it].name
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
internal fun MetadataBlock(modifier: Modifier = Modifier, game: Game) {
    Row(modifier = modifier.fillMaxWidth()) {
        RatingMetadata(
            modifier = Modifier
                .weight(0.3f)
                .padding(5.dp),
            rating = game.totalRating.toInt(),
            totalRatingCount = game.totalRatingCount
        )
        ReleaseMetadata(
            modifier = Modifier
                .weight(0.3f)
                .padding(5.dp),
            releaseDate = game.firstReleaseDate.seconds
        )
        GameModeMetadata(
            modifier = Modifier
                .weight(0.3f)
                .padding(5.dp),
            mode = game.gameModesList.first()
        )
    }
}

@Composable
internal fun GameModeMetadata(modifier: Modifier = Modifier, mode: GameMode) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val icon = when (mode.id.toInt()) {
                1 -> Icons.Default.Person
                2 -> Icons.Default.Group
                3 -> Icons.Default.Group
                4 -> Icons.Default.Splitscreen
                else -> Icons.Default.Public
            }
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier.padding(end = 5.dp)
            )
        }
        Text(
            text = mode.name,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Composable
internal fun ReleaseMetadata(modifier: Modifier, releaseDate: Long) {
    val timeToRelease =
        Instant.now().until(Instant.ofEpochSecond(releaseDate), ChronoUnit.DAYS)
    val dateOfRelease =
        DateTimeFormatter.ofPattern("MM/dd/yyyy")
            .format(
                Instant.ofEpochSecond(releaseDate).atZone(ZoneId.systemDefault())
                    .toLocalDate()
            )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Event,
                contentDescription = "",
                modifier = Modifier.padding(end = 5.dp)
            )
            val releaseText = if (timeToRelease > 0) {
                "$timeToRelease days"
            } else {
                "Released"
            }
            Text(
                text = releaseText,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Text(
            text = dateOfRelease,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Composable
internal fun RatingMetadata(
    modifier: Modifier = Modifier,
    rating: Int,
    totalRatingCount: Int
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "",
                modifier = Modifier.padding(end = 5.dp)
            )
            val ratingText = if (rating == 0) {
                "TBD"
            } else {
                "$rating"
            }
            Text(
                text = ratingText,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        if (rating != 0) {
            Text(
                text = "out of $totalRatingCount reviews",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Composable
internal fun ExpandableSummary(summary: String) {
    var expanded by remember { mutableStateOf(false) }
    Text(
        text = summary,
        style = MaterialTheme.typography.bodyLarge,
        maxLines = if (expanded) 100 else 5,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.clickable { expanded = !expanded }
    )
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
