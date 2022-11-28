package com.adewan.mystuff.feature.expanded

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.AsyncImage
import com.adewan.mystuff.common.ux.CenteredLoadingIndicator
import com.adewan.mystuff.core.models.navigation.ExpandedViewArgs
import org.koin.androidx.compose.getViewModel

@Composable
fun ExpandedView(
    modifier: Modifier = Modifier,
    viewModel: ExpandedViewModel = getViewModel(),
    args: ExpandedViewArgs,
    navigateBack: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.loadList(args = args)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = navigateBack) {
                    Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = "")
                }
            },
            title = { Text(text = args.title, fontWeight = FontWeight.Bold) }
        )
        when (viewState) {
            ExpandedViewState.Loading -> CenteredLoadingIndicator()
            ExpandedViewState.Error -> ErrorView()
            is ExpandedViewState.Results -> ExpandedList(data = (viewState as ExpandedViewState.Results))
        }
    }
}

@Composable
internal fun ExpandedList(data: ExpandedViewState.Results) {
    BoxWithConstraints {
        val width = maxWidth / 3
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(data.result.size) {
                val item = data.result[it]
                AsyncImage(
                    model = item.poster,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(width)
                        .height(height = max(width * 0.67f, 175.dp))
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                )
            }
        }
    }
}

@Composable
internal fun ErrorView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_illus),
            contentDescription = "Library is empty",
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = "Uh Oh! \n Something went wrong",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}
