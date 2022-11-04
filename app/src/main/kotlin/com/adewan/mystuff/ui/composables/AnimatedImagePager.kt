@file:OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)

package com.adewan.mystuff.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@Composable
fun AnimatedImagePager(
    modifier: Modifier = Modifier,
    state: PagerState,
    images: List<String>,
    imageSize: DpSize,
    paddingSize: Dp,
    onImageTap: (Int) -> Unit,
) {
    HorizontalPager(
        count = images.size,
        state = state,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = paddingSize),
    ) { page ->
        Card(
            modifier = Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85.dp,
                        stop = 1.dp,
                        fraction = 1 - pageOffset.coerceIn(0f, 1f),
                    ).also { scale ->
                        scaleX = scale.value
                        scaleY = scale.value
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5.dp,
                        stop = 1.dp,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f),
                    ).value
                }
                .clickable {
                    onImageTap(page)
                },
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(imageSize),
                model = images[page],
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
    }
}
