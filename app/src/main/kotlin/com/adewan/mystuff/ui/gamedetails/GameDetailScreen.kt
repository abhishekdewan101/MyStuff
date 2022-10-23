@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)

package com.adewan.mystuff.ui.gamedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adewan.mystuff.ui.composables.AnimatedImagePager
import com.adewan.mystuff.ui.composables.CenteredLoadingIndicator
import com.adewan.mystuff.ui.composables.RatingBar
import com.adewan.mystuff.ui.composables.TitledTextBlock
import com.adewan.mystuff.ui.navigation.NavigationDirector
import com.adewan.mystuff.ui.utils.buildYoutubeIntent
import com.adewan.mystuff.ui.utils.buildYoutubeScreenshotUrl
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.compose.get

@Composable
fun GameDetailScreen(
    navigationDirector: NavigationDirector,
    identifier: String,
    viewModel: GameDetailViewModel = get()
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel) {
        viewModel.requestGameDetailForIdentifier(identifier = identifier)
    }

    if (viewState != null) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = "https://images.igdb.com/igdb/image/upload/t_720p/${viewState!!.artworksList.random().imageId}.jpg",
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.0f),
                                    Color.Black
                                )
                            )
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = viewState!!.name,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center
                    )
                    Row {
                        viewState!!.themesList.map {
                            ElevatedAssistChip(
                                onClick = { },
                                label = { Text(text = it.name) },
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                RatingBar(
                    rating = viewState!!.totalRating,
                    totalNumberOfRatings = viewState!!.totalRatingCount
                )
            }

            TitledTextBlock(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(horizontal = 15.dp),
                title = "Summary",
                bodyText = viewState!!.summary
            )

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val maxWidth = maxWidth
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        "Screenshots",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    AnimatedImagePager(
                        state = rememberPagerState(),
                        images = viewState!!.screenshotsList.map { "https://images.igdb.com/igdb/image/upload/t_720p/${it.imageId}.jpg" },
                        onImageTap = {
                            navigationDirector.navigateToExpandedImageview(url = "https://images.igdb.com/igdb/image/upload/t_1080p/${viewState!!.screenshotsList[it].imageId}.jpg")
                        },
                        imageSize = DpSize(width = maxWidth, height = 250.dp),
                        paddingSize = maxWidth.div(8)
                    )
                }
            }

            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val maxWidth = maxWidth
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        "Videos",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        items(viewState!!.videosList) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.clickable {
                                    navigationDirector.navigateToExternalIntent(
                                        it.buildYoutubeIntent(),
                                        context
                                    )
                                }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 5.dp)
                                ) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .size(width = maxWidth - 40.dp, height = 200.dp),
                                        model = it.buildYoutubeScreenshotUrl(),
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop
                                    )
                                    Icon(
                                        Icons.Default.PlayArrow,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(42.dp)
                                            .align(Alignment.Center)
                                            .clip(CircleShape)
                                            .background(Color.Black.copy(alpha = 0.5f)),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }

                                Text(
                                    it.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                        }
                    }
                }
            }

            TitledTextBlock(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(horizontal = 15.dp),
                title = "Storyline",
                bodyText = viewState!!.storyline
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    "Available on",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(viewState!!.platformsList) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(15.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = "https://images.igdb.com/igdb/image/upload/t_720p/${it.platformLogo.imageId}.png",
                                contentDescription = "",
                                contentScale = ContentScale.Inside
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(
                    "Similar games",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .padding(horizontal = 15.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(start = 5.dp)
                ) {
                    items(viewState!!.similarGamesList) {
                        Card(onClick = { navigationDirector.navigateToGameDetails(it.slug) }) {
                            AsyncImage(
                                modifier = Modifier.size(100.dp, 150.dp),
                                model = "https://images.igdb.com/igdb/image/upload/t_720p/${it.cover.imageId}.png",
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    } else {
        CenteredLoadingIndicator()
    }
}
