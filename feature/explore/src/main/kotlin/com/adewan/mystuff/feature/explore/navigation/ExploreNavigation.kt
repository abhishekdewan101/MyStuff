package com.adewan.mystuff.feature.explore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.adewan.mystuff.feature.explore.ExploreScreen

const val exploreRoute = "explore_route"

fun NavController.navigateToExploreRoute(navOptions: NavOptions? = null) {
    this.navigate(exploreRoute, navOptions)
}

fun NavGraphBuilder.exploreGraph(nestedGraph: NavGraphBuilder.() -> Unit) {
    composable(exploreRoute) {
        ExploreScreen(navigateToSearchScreen = {}, navigateToAccountScreen = {})
    }
    nestedGraph()
}
