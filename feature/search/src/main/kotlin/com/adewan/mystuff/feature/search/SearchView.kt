package com.adewan.mystuff.feature.search

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import com.adewan.mystuff.common.ux.CenteredLoadingIndicator
import com.adewan.mystuff.common.ux.ChipsRowItem
import com.adewan.mystuff.common.ux.SingleSelectFilterChipRow
import com.adewan.mystuff.core.data.repositories.SearchResult
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(modifier: Modifier = Modifier, viewModel: SearchViewModel = getViewModel()) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val keyboard = LocalSoftwareKeyboardController.current

    val viewState by viewModel.viewState.collectAsState()

    DisposableEffect(key1 = viewModel) {
        onDispose {
            viewModel.clearSearchResults()
        }
    }

    Column(modifier = modifier.padding(horizontal = 10.dp)) {
        CenterAlignedTopAppBar(title = { Text(text = "Search", fontWeight = FontWeight.Bold) })
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            },
            placeholder = {
                Text(
                    text = "Search...",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = MaterialTheme.colorScheme.onBackground,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
                textColor = MaterialTheme.colorScheme.onBackground,
                placeholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            ),
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions {
                keyboard?.hide()
                viewModel.startSearch(searchText.text)
            }
        )
        SingleSelectFilterChipRow(
            modifier = Modifier.padding(top = 5.dp),
            chips = listOf(
                ChipsRowItem(label = "All", id = "All"),
                ChipsRowItem(label = "Games", id = "Games"),
                ChipsRowItem(label = "Movies", id = "Movies"),
                ChipsRowItem(label = "Shows", id = "Shows")
            ),
            onTap = {}
        )

        when (viewState) {
            SearchViewState.Start -> StartSearch()
            SearchViewState.Loading -> CenteredLoadingIndicator()
            is SearchViewState.Results -> SearchResults(results = (viewState as SearchViewState.Results).results)
        }
    }
}

@Composable
internal fun SearchResults(modifier: Modifier = Modifier, results: List<SearchResult>) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(results) {
            SearchResultItem(result = it)
        }
    }
}

@Composable
internal fun SearchResultItem(result: SearchResult) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = result.poster,
            contentDescription = result.name + " poster",
            contentScale = ContentScale.Crop,
            fallback = painterResource(id = R.drawable.start_search),
            modifier = Modifier
                .width(75.dp)
                .height(125.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
        )
        Column(
            modifier = Modifier.padding(start = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = result.name,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(text = result.releasing, style = MaterialTheme.typography.bodyMedium)
            Text(text = result.rating, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
internal fun StartSearch(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val drawable = context.getDrawable(R.drawable.start_search)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        replaceColor(
            drawable?.toBitmap(),
            Color(0xFF6c63ff).toArgb(),
            MaterialTheme.colorScheme.secondaryContainer.toArgb()
        )?.asImageBitmap()?.let {
            Image(
                bitmap = it,
                contentDescription = "Library is empty",
                modifier = Modifier.size(128.dp)
            )
        }
        Text(
            text = "Start your search",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}

fun replaceColor(src: Bitmap?, fromColor: Int, targetColor: Int): Bitmap? {
    if (src == null) {
        return null
    }
    // Source image size
    val width: Int = src.width
    val height: Int = src.height
    val pixels = IntArray(width * height)
    // get pixels
    src.getPixels(pixels, 0, width, 0, 0, width, height)
    for (x in pixels.indices) {
        pixels[x] = if (pixels[x] == fromColor) targetColor else pixels[x]
    }
    // create result bitmap output
    val result: Bitmap = Bitmap.createBitmap(width, height, src.getConfig())
    // set pixels
    result.setPixels(pixels, 0, width, 0, 0, width, height)
    return result
}
