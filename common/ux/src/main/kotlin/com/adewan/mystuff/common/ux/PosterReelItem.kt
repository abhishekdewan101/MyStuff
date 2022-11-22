package com.adewan.mystuff.common.ux

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adewan.mystuff.common.theme.MyStuffTheme

data class PosterReelItemData(
    val posterUrl: String,
    val title: String,
    val rating: Double,
    val maxRating: Double,
    val chipsData: List<String>
)

@Composable
fun PosterReelItem(modifier: Modifier = Modifier, data: PosterReelItemData) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 3.dp,
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .height(175.dp)
                    .width(125.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Red)
            ) {
                AsyncImage(
                    model = data.posterUrl,
                    contentDescription = data.title,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = data.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                RatingBar(
                    rating = data.rating,
                    maxRating = data.maxRating,
                    iconTint = MaterialTheme.colorScheme.onSecondaryContainer,
                    iconSize = 18.dp
                )
                Spacer(modifier = Modifier.height(10.dp))
                ChipsRow(
                    chips = data.chipsData,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textColor = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewPosterReelItem() {
    MyStuffTheme {
        Column(Modifier.fillMaxSize()) {
            PosterReelItem(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                data =
                PosterReelItemData(
                    posterUrl = "",
                    title = "Spider Man - Miles Morales (Game of the year edition)",
                    rating = 88.0,
                    maxRating = 100.0,
                    chipsData = listOf("Action", "Story", "Horror")
                )
            )
        }
    }
}
