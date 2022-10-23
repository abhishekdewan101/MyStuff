package com.adewan.mystuff.ui.moviedetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.adewan.mystuff.ui.navigation.NavigationDirector
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieDetailScreen(
    navigationDirector: NavigationDirector,
    identifier: Int,
    viewModel: MovieDetailViewModel = getViewModel()
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.getMovieDetails(identifier = identifier)
    }
}
