package com.adewan.mystyuff.feature.games.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.adewan.mystyuff.feature.games.GameListScreen
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val gamesListRoute = "games_list_route"

@kotlinx.serialization.Serializable
data class GameListNavArgs(val title: String, val query: String)

fun NavController.navigateToGamesList(gameListNavArgs: GameListNavArgs) {
    val encodedValue = Uri.encode(Json.encodeToString(gameListNavArgs))
    this.navigate(route = "$gamesListRoute/$encodedValue")
}

fun NavGraphBuilder.gameListScreen() {
    composable(
        route = "$gamesListRoute/{args}",
        arguments = listOf(
            navArgument("args") { type = NavType.StringType }
        )
    ) {
        val args =
            Json.decodeFromString<GameListNavArgs>(Uri.decode(it.arguments?.getString("args")))
        GameListScreen(args = args)
    }
}
