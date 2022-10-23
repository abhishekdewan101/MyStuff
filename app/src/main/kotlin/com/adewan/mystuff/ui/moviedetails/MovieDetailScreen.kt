package com.adewan.mystuff.ui.moviedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adewan.mystuff.ui.composables.FlowableTextChipRow
import com.adewan.mystuff.ui.composables.GradientScrimContainer
import com.adewan.mystuff.ui.navigation.NavigationDirector
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieDetailScreen(
    navigationDirector: NavigationDirector,
    identifier: String,
    viewModel: MovieDetailViewModel = getViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()

    if (viewState != null) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            GradientScrimContainer(
                backgroundContent = {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        model = viewState!!.backdropUrl,
                        contentDescription = "",
                        contentScale = ContentScale.FillHeight
                    )
                },
                foregroundContent = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = viewState!!.title!!,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 15.dp)
                        )
                        viewState!!.genres?.let { genres ->
                            FlowableTextChipRow(chips = genres.map { it.name })
                        }
                    }
                },
                scrimHeight = 100.dp,
                scrimGradient = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    )
                )
            )
        }
    }

    LaunchedEffect(key1 = viewModel) {
        viewModel.getMovieDetails(identifier = identifier)
    }
}
