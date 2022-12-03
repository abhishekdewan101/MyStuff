package com.adewan.mystuff.feature.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val splashRoute = "splash_route"

fun NavGraphBuilder.splashView(showBottomBar: (Boolean) -> Unit, navigateToLibrary: () -> Unit) {
    showBottomBar(false)
    composable(splashRoute) {
        SplashView(navigateToLibrary = navigateToLibrary)
    }
}
