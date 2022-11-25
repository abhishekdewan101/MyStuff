package com.adewan.mystuff.feature.library

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val libraryRoute = "library_route"

fun NavController.navigateToLibraryView() {
    this.navigate(libraryRoute)
}

fun NavGraphBuilder.libraryView(updateBottomBar: (Boolean) -> Unit) {
    composable(route = libraryRoute) {
        updateBottomBar(true)
        LibraryView()
    }
}
