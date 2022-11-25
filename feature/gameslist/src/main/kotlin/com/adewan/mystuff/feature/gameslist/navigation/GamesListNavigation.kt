package com.adewan.mystuff.feature.gameslist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.adewan.mystuff.feature.gameslist.GameListScreen

private const val gamesListRoute = "games_list"

fun NavController.navigateToGamesList() {
    this.navigate(gamesListRoute)
}

fun NavGraphBuilder.gamesListScreen() {
    composable(gamesListRoute) {
        GameListScreen()
    }
}
