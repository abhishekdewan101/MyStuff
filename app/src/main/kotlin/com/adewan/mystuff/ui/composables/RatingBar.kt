package com.adewan.mystuff.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(rating: Double, maxRating: Double, totalNumberOfRatings: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .width(200.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            var leftRating = rating
            repeat(5) {
                val icon = if (leftRating > (maxRating * .2)) {
                    Icons.Filled.StarRate
                } else if (leftRating < (maxRating * .2) && leftRating > 0) {
                    Icons.Filled.StarHalf
                } else {
                    Icons.Outlined.StarRate
                }

                Icon(
                    icon,
                    contentDescription = "",
                    tint = Color.Yellow,
                    modifier = Modifier.size(24.dp)
                )
                leftRating -= (maxRating * .2)
            }
            Text(
                text = String.format("%.1f", rating),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Text(
            "$totalNumberOfRatings ratings",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Preview
@Composable
fun PreviewRatingBar() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RatingBar(88.0, 100.0, 1000)
        Spacer(modifier = Modifier.height(10.dp))
        RatingBar(44.0, 100.0, 1000)
        Spacer(modifier = Modifier.height(10.dp))
        RatingBar(90.0, 100.0, 1000)
        Spacer(modifier = Modifier.height(10.dp))
        RatingBar(91.0, 100.0, 1000)
        Spacer(modifier = Modifier.height(10.dp))
        RatingBar(85.0, 100.0, 1000)
        Spacer(modifier = Modifier.height(10.dp))
        RatingBar(81.0, 100.0, 1000)
        Spacer(modifier = Modifier.height(10.dp))
        RatingBar(79.0, 100.0, 1000)
        Spacer(modifier = Modifier.height(10.dp))
    }
}
