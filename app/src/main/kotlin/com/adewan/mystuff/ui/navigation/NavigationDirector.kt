package com.adewan.mystuff.ui.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.navigation.NavHostController

interface NavigationDirector {
    fun navigateToHome()
    fun navigateToGameDetails(identifier: String)
    fun navigateToExpandedImageview(url: String)
    fun navigateToExternalIntent(intent: Intent, context: Context)
}

class NavigationDirectorImpl(private val navHostController: NavHostController) :
    NavigationDirector {

    override fun navigateToHome() {
        navHostController.navigate(NavDestination.Home.route) {
            popUpTo(NavDestination.Splash.route) {
                inclusive = true
            }
        }
    }

    override fun navigateToGameDetails(identifier: String) {
        val encodedRoute = NavDestination.GameDetail.route.replace("{identifier}", identifier)
        navHostController.navigate(encodedRoute)
    }

    override fun navigateToExpandedImageview(url: String) {
        val encodedRoute =
            NavDestination.ExpandedImageViewer.route.replace("{url}", Uri.encode(url))
        navHostController.navigate(encodedRoute)
    }

    override fun navigateToExternalIntent(intent: Intent, context: Context) {
        context.startActivity(intent)
    }
}
