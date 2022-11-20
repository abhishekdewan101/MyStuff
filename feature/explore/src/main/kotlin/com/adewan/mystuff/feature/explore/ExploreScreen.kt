package com.adewan.mystuff.feature.explore

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.common.theme.MyStuffTheme
import com.adewan.mystuff.common.ux.TappableSearchBar
import com.adewan.mystuff.common.ux.ThemedContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(navigateToSearchScreen: () -> Unit, navigateToAccountScreen: () -> Unit) {
    ThemedContainer {
        Scaffold(topBar = { TopBarRow(navigateToSearchScreen, navigateToAccountScreen) }) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {
            }
        }
    }
}

@Composable
private fun TopBarRow(navigateToSearchScreen: () -> Unit, navigateToAccountScreen: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(64.dp)
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TappableSearchBar(
            modifier = Modifier
                .weight(0.6f)
                .padding(horizontal = 10.dp),
            onTap = navigateToSearchScreen
        )
        Box(
            modifier = Modifier
                .weight(0.1f)
                .padding(end = 10.dp)
        ) {
            IconButton(onClick = { navigateToAccountScreen() }) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(10.dp)
                        .clickable { }
                )
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewExploreScreen() {
    MyStuffTheme {
        ExploreScreen(navigateToSearchScreen = {}, navigateToAccountScreen = {})
    }
}