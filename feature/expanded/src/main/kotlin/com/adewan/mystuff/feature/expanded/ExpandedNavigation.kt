package com.adewan.mystuff.feature.expanded

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.adewan.mystuff.core.models.navigation.ExpandedViewArgs
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val expandedRoute = "expanded_route/{args}"

fun NavController.navigateToExpandedView(args: ExpandedViewArgs) {
    val encodedUri = Uri.encode(Json.encodeToString(args))
    this.navigate(expandedRoute.replace("{args}", encodedUri))
}

fun NavGraphBuilder.expandedView(showBottomBar: (Boolean) -> Unit) {
    composable(
        expandedRoute,
        arguments = listOf(navArgument("args") { type = NavType.StringType })
    ) {
        val args = it.arguments?.getString("args")
            ?: throw IllegalStateException("Cannot navigate to expanded view without valid args")
        val expandedViewArgs = Json.decodeFromString<ExpandedViewArgs>(args)
        showBottomBar(false)
        ExpandedView(args = expandedViewArgs)
    }
}
