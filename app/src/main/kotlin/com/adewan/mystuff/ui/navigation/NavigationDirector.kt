package com.adewan.mystuff.ui.navigation

import android.net.Uri
import androidx.navigation.NavHostController

class NavigationDirector(private val navHostController: NavHostController) {
    fun navigateToGameDetails(identifier: String) {
        val encodedRoute = NavDestination.GameDetail.route.replace("{identifier}", identifier)
        navHostController.navigate(encodedRoute)
    }

    fun navigateToExpandedImageView(url: String) {
        val encodedRoute =
            NavDestination.ExpandedImageViewer.route.replace("{url}", Uri.encode(url))
        navHostController.navigate(encodedRoute)
    }
}
