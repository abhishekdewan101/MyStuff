package com.adewan.mystuff.features.landing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.adewan.mystuff.features.landing.LandingScreen

const val landingRoute = "landing_route"

fun NavController.navigateToLandingRoute(navOptions: NavOptions? = null) {
    this.navigate(landingRoute, navOptions)
}

fun NavGraphBuilder.landingScreen(navigateToExplore: () -> Unit) {
    composable(landingRoute) {
        LandingScreen(navigateToExplore = navigateToExplore)
    }
}
