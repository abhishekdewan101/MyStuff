package com.adewan.mystuff.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adewan.mystuff.ui.home.HomeScreen

@Composable
fun AppNavGraph() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = NavDestination.Home.route) {
        composable(NavDestination.Home.route) {
            HomeScreen()
        }
    }
}
