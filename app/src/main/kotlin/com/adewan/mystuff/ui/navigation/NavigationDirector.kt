package com.adewan.mystuff.ui.navigation

import androidx.navigation.NavHostController

class NavigationDirector(private val navHostController: NavHostController) {
    fun navigateToGameDetails(identifier: String) {
        val encodedRoute = NavDestination.GameDetail.route.replace("{identifier}", identifier)
        navHostController.navigate(encodedRoute)
    }
}
