package com.adewan.mystuff.common.ux

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class PosterGridWithTitleData(
    val title: String,
    val urlIdPairs: Map<String, String> // url to id
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PosterGridWithTitle(
    modifier: Modifier = Modifier,
    data: PosterGridWithTitleData,
    onSeeAllTap: () -> Unit,
    onTap: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data.title,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            TextButton(onClick = onSeeAllTap) {
                Text(text = "See all")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        data.urlIdPairs.entries.chunked(3).forEach {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                it.forEach {
                    Card(onClick = { onTap(it.value) }) {
                        AsyncImage(
                            modifier = Modifier
                                .size(width = 125.dp, height = 175.dp),
                            model = it.key,
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}
