package com.adewan.mystuff.ui.navigation

sealed class NavDestination(val route: String) {
    object Home : NavDestination(route = "home")
}
