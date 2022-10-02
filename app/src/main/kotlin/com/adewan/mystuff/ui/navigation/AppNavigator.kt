package com.adewan.mystuff.ui.navigation

import androidx.navigation.NavHostController

class AppNavigator(private val navHostController: NavHostController) {
    fun navigateToGameScreen() {
        navHostController.navigate(NavDestination.Game.route)
    }
}
