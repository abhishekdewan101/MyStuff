package com.adewan.mystuff.feature.explore

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val exploreRoute = "explore_route"

fun NavController.navigateToExploreView(navOptions: NavOptions) {
    this.navigate(exploreRoute, navOptions)
}

fun NavGraphBuilder.exploreView(updateBottomBar: (Boolean) -> Unit) {
    composable(exploreRoute) {
        updateBottomBar(true)
        Exploreview(
            navigateToSearchScreen = {},
            navigateToAccountScreen = {},
            navigateToGamesList = {}
        )
    }
}
