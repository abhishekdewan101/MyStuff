package com.adewan.mystuff.common.ux

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(rating: Double, maxRating: Double, iconTint: Color, iconSize: Dp) {
    Row(
        modifier = Modifier
            .width(200.dp),
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
                tint = iconTint,
                modifier = Modifier.size(iconSize)
            )
            leftRating -= (maxRating * .2)
        }
    }
}

@Preview
@Composable
fun PreviewRatingBar() {
    RatingBar(88.0, 100.0, iconTint = Color.Yellow, iconSize = 24.dp)
}
