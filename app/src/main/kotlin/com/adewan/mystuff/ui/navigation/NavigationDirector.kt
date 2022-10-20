package com.adewan.mystuff.ui.navigation

import android.content.Context
import android.content.Intent
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

    fun navigateToExternalIntent(intent: Intent, context: Context) {
        context.startActivity(intent)
    }
}
