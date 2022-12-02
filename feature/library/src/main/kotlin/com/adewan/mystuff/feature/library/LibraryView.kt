package com.adewan.mystuff.feature.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.common.ux.CenteredLoadingIndicator
import org.koin.androidx.compose.getViewModel

@Composable
fun LibraryView(
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = getViewModel(),
    navigateToAccountView: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    Column(modifier = modifier.padding(horizontal = 10.dp)) {
        CenterAlignedTopAppBar(
            title = { Text(text = "Library", fontWeight = FontWeight.Bold) },
            actions = { UserAvatar(navigateToAccountView = navigateToAccountView) }
        )
        when (viewState) {
            LibraryViewState.Loading -> CenteredLoadingIndicator()
            LibraryViewState.Empty -> EmptyLibrary()
            LibraryViewState.Results -> LibraryList()
        }
    }
}

@Composable
internal fun LibraryList() {
}

@Composable
internal fun UserAvatar(navigateToAccountView: () -> Unit) {
    IconButton(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondaryContainer),
        onClick = navigateToAccountView
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = ""
        )
    }
}

@Composable
internal fun EmptyLibrary(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_illus),
            contentDescription = "Library is empty",
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = "Library is empty",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}
