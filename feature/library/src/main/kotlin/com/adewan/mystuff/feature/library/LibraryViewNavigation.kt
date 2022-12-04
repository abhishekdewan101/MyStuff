package com.adewan.mystuff.feature.library

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val libraryRoute = "library_route"

fun NavController.navigateToLibraryView(navOptions: NavOptions) {
    this.navigate(libraryRoute, navOptions)
}

fun NavGraphBuilder.libraryView(
    showBottomBar: (Boolean) -> Unit,
    navigateToAccountView: () -> Unit,
    navigateToDetailView: (String) -> Unit
) {
    composable(route = libraryRoute) {
        showBottomBar(true)
        LibraryView(
            navigateToAccountView = navigateToAccountView,
            navigateToDetailView = navigateToDetailView
        )
    }
}
