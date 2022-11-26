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
    updateBottomBar: (Boolean) -> Unit,
    navigateToAccountView: () -> Unit
) {
    composable(route = libraryRoute) {
        updateBottomBar(true)
        LibraryView(navigateToAccountView = navigateToAccountView)
    }
}
