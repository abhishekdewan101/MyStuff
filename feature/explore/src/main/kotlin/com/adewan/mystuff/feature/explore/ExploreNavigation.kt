package com.adewan.mystuff.feature.explore

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.adewan.mystuff.core.models.navigation.ExpandedViewArgs

const val exploreRoute = "explore_route"

fun NavController.navigateToExploreView(navOptions: NavOptions) {
    this.navigate(exploreRoute, navOptions)
}

fun NavGraphBuilder.exploreView(
    showBottomBar: (Boolean) -> Unit,
    navigateToExpandedView: (ExpandedViewArgs) -> Unit,
    navigateToDetailView: (String) -> Unit
) {
    composable(exploreRoute) {
        showBottomBar(true)
        ExploreView(
            navigateToExpandedView = navigateToExpandedView,
            navigateToDetailView = navigateToDetailView
        )
    }
}
