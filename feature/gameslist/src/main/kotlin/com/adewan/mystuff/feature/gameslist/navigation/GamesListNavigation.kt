package com.adewan.mystuff.feature.gameslist.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.adewan.mystuff.feature.gameslist.GameListScreen

fun NavController.navigateToGamesList(listQuery: String) {
    val encodedQuery = Uri.encode(listQuery)
    this.navigate("games_list/$encodedQuery")
}

fun NavGraphBuilder.gamesListScreen() {
    composable(
        "games_list/{listQuery}",
        arguments = listOf(
            navArgument("listQuery") { type = NavType.StringType }
        )
    ) {
        val listQuery = Uri.decode(it.arguments?.getString("listQuery"))
        GameListScreen(listQuery = listQuery)
    }
}
