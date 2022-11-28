package com.adewan.mystuff.feature.expanded

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val expandedRoute = "expanded_route"

fun NavController.navigateToExpandedView() {
    this.navigate(expandedRoute)
}

fun NavGraphBuilder.expandedView(showBottomBar: (Boolean) -> Unit) {
    composable(expandedRoute) {
        showBottomBar(false)
        ExpandedView()
    }
}
