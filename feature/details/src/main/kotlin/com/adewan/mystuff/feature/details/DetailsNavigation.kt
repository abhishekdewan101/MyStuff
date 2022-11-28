package com.adewan.mystuff.feature.details

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val detailsRoute = "details_route/{args}"

fun NavController.navigateToDetailsView(id: String) {
    this.navigate(detailsRoute.replace("{args}", Uri.encode(id)))
}

fun NavGraphBuilder.detailsView(showBottomBar: (Boolean) -> Unit, navigateBack: () -> Unit) {
    composable(
        detailsRoute,
        arguments = listOf(navArgument("args") { type = NavType.StringType })
    ) {
        val args = it.arguments?.getString("args")
            ?: throw IllegalStateException("Cannot navigate to details view without a valid id")
        val id = Uri.decode(args)
        showBottomBar(false)
        DetailsView(id = id, navigateBack = navigateBack)
    }
}
