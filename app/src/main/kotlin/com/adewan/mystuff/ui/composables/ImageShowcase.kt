@file:OptIn(ExperimentalPagerApi::class)

package com.adewan.mystuff.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

data class ImageShowcaseItem(val identifier: String, val url: String, val label: String? = null)

@Composable
fun ImageShowcase(
    modifier: Modifier = Modifier,
    items: List<ImageShowcaseItem>,
    showLabel: Boolean = true,
    onImageTap: (Int) -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = 1)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedImagePager(
            modifier = modifier,
            state = pagerState,
            images = items.map { it.url },
            onImageTap = onImageTap,
            imageSize = DpSize(width = 200.dp, height = 250.dp),
            paddingSize = 100.dp,
        )
        if (showLabel) {
            Text(
                items[pagerState.currentPage].label
                    ?: throw IllegalStateException("Label can't be null with showLabel is true"),
                modifier = Modifier
                    .padding(top = 25.dp)
                    .padding(horizontal = 10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight =
                    FontWeight.Bold,
                ),
            )
        }
    }
}
