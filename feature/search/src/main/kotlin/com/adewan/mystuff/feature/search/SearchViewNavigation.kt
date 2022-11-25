package com.adewan.mystuff.feature.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val searchRoute = "search_route"

fun NavController.navigateToSearchView() {
    this.navigate(searchRoute)
}

fun NavGraphBuilder.searchView(updateBottomBar: (Boolean) -> Unit) {
    composable(route = searchRoute) {
        updateBottomBar(true)
        SearchView()
    }
}
