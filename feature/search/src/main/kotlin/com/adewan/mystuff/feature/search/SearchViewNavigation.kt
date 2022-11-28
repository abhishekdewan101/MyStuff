package com.adewan.mystuff.feature.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val searchRoute = "search_route"

fun NavController.navigateToSearchView(navOptions: NavOptions) {
    this.navigate(searchRoute, navOptions)
}

fun NavGraphBuilder.searchView(
    showBottomBar: (Boolean) -> Unit,
    navigateToDetailView: (String) -> Unit
) {
    composable(route = searchRoute) {
        showBottomBar(true)
        SearchView(navigateToDetailView = navigateToDetailView)
    }
}
