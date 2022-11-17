package com.adewan.mystuff.features.landing.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

internal data class SlideData(
    val image: Int,
    val contentDescription: String? = null,
    val title: String,
    val message: String
)

@Composable
internal fun Slide(data: SlideData) {
    Column {
        Image(
            painter = painterResource(id = data.image),
            contentDescription = data.contentDescription,
            modifier = Modifier
                .padding(20.dp)
                .size(300.dp)
        )
        Text(
            data.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            data.message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}
