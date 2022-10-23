package com.adewan.mystuff.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val showBottomBar: Boolean = true
) {
    object Splash : NavDestination(
        route = "splash",
        label = "Splash",
        icon = Icons.Outlined.Apps,
        showBottomBar = false
    )

    object Home : NavDestination(route = "home", label = "Home", icon = Icons.Outlined.Home)
    object Search : NavDestination(route = "search", label = "Search", icon = Icons.Outlined.Search)
    object Library :
        NavDestination(route = "library", label = "My Stuff", icon = Icons.Outlined.Apps)

    object GameDetail :
        NavDestination(route = "game_detail/{identifier}", label = "", icon = Icons.Outlined.Apps)

    object ExpandedImageViewer :
        NavDestination(
            route = "expandedImage/{url}",
            label = "",
            icon = Icons.Outlined.Apps,
            showBottomBar = false
        )

    object MovieDetail :
        NavDestination(route = "movie_detail/{identifier}", label = "", icon = Icons.Outlined.Apps)
}

val routes = listOf(NavDestination.Home, NavDestination.Search, NavDestination.Library)
