package com.adewan.mystuff.feature.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.common.ux.CenteredLoadingIndicator
import com.adewan.mystuff.common.ux.ErrorView
import org.koin.androidx.compose.getViewModel
import proto.Game

@Composable
fun DetailsView(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = getViewModel(),
    id: String,
    navigateBack: () -> Unit
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.loadDetails(id)
    }

    val viewState by viewModel.viewState.collectAsState()
    when (viewState) {
        DetailsViewState.Loading -> CenteredLoadingIndicator()
        DetailsViewState.Error -> ErrorView(message = "Uh Oh! \n Something went wrong")
        is DetailsViewState.Result -> Details(
            game = (viewState as DetailsViewState.Result).data,
            navigateBack = navigateBack
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Details(game: Game, navigateBack: () -> Unit) {
    Scaffold(topBar = { DetailsTopBar(title = game.name, navigateBack = navigateBack) }) {
        LazyColumn() {
            items(100) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(height = 175.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .padding(10.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                )
            }
        }
    }
}

@Composable
private fun DetailsTopBar(title: String, navigateBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = "Back")
            }
        }
    )
}
