package com.adewan.mystuff.core.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Podcasts
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.adewan.mystuff.common.ux.NoRippleInteractionSource
import com.adewan.mystuff.feature.account.accountView
import com.adewan.mystuff.feature.account.navigateToAccountView
import com.adewan.mystuff.feature.details.detailsView
import com.adewan.mystuff.feature.details.navigateToDetailsView
import com.adewan.mystuff.feature.expanded.expandedView
import com.adewan.mystuff.feature.expanded.navigateToExpandedView
import com.adewan.mystuff.feature.explore.exploreRoute
import com.adewan.mystuff.feature.explore.exploreView
import com.adewan.mystuff.feature.explore.navigateToExploreView
import com.adewan.mystuff.feature.library.libraryRoute
import com.adewan.mystuff.feature.library.libraryView
import com.adewan.mystuff.feature.library.navigateToLibraryView
import com.adewan.mystuff.feature.search.navigateToSearchView
import com.adewan.mystuff.feature.search.searchRoute
import com.adewan.mystuff.feature.search.searchView
import com.adewan.mystuff.feature.splash.splashRoute
import com.adewan.mystuff.feature.splash.splashView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MsApp(modifier: Modifier = Modifier, userOnBoarded: Boolean) {
    var bottomBarPresent by remember { mutableStateOf(false) }
    val navHostController = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            MsAppBottomBar(
                navController = navHostController,
                bottomBarPresent = bottomBarPresent
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(
                navController = navHostController,
                modifier = Modifier.fillMaxSize(),
                startDestination = if (userOnBoarded) libraryRoute else splashRoute
            ) {
                splashView(
                    showBottomBar = { showBottomBar -> bottomBarPresent = showBottomBar },
                    navigateToLibrary = {
                        navHostController.navigateToLibraryView(
                            navOptions {
                                popUpTo(splashRoute) {
                                    inclusive = true
                                }
                            }
                        )
                    }
                )
                searchView(
                    showBottomBar = { showBottomBar -> bottomBarPresent = showBottomBar },
                    navigateToDetailView = { id -> navHostController.navigateToDetailsView(id) }
                )
                libraryView(
                    showBottomBar = { showBottomBar -> bottomBarPresent = showBottomBar },
                    navigateToAccountView = { navHostController.navigateToAccountView() }
                )
                exploreView(
                    showBottomBar = { showBottomBar -> bottomBarPresent = showBottomBar },
                    navigateToExpandedView = { args -> navHostController.navigateToExpandedView(args = args) },
                    navigateToDetailView = { id -> navHostController.navigateToDetailsView(id) }
                )
                accountView(showBottomBar = { showBottomBar -> bottomBarPresent = showBottomBar })
                expandedView(
                    showBottomBar = { showBottomBar -> bottomBarPresent = showBottomBar },
                    navigateBack = { navHostController.popBackStack() },
                    navigateToDetailView = { id -> navHostController.navigateToDetailsView(id) }
                )
                detailsView(
                    showBottomBar = { showBottomBar -> bottomBarPresent = showBottomBar },
                    navigateBack = { navHostController.popBackStack() },
                    navigateToDetailView = { id -> navHostController.navigateToDetailsView(id) }
                )
            }
        }
    }
}

val topLevelNavOptions = { id: Int ->
    navOptions {
        launchSingleTop = true
        restoreState = true
        popUpTo(id) {
            saveState = true
        }
    }
}

@Composable
internal fun MsAppBottomBar(navController: NavController, bottomBarPresent: Boolean) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    AnimatedVisibility(
        visible = bottomBarPresent,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
    ) {
        NavigationBar(modifier = Modifier.fillMaxWidth(), containerColor = Color.Transparent) {
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == searchRoute } == true,
                onClick = { navController.navigateToSearchView(topLevelNavOptions(navController.graph.startDestinationId)) },
                interactionSource = NoRippleInteractionSource,
                icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
                label = { Text(text = "Search") }
            )
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == libraryRoute } == true,
                onClick = { navController.navigateToLibraryView(topLevelNavOptions(navController.graph.startDestinationId)) },
                interactionSource = NoRippleInteractionSource,
                icon = { Icon(imageVector = Icons.Default.Apps, contentDescription = "Library") },
                label = { Text(text = "Library") }
            )
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == exploreRoute } == true,
                onClick = { navController.navigateToExploreView(topLevelNavOptions(navController.graph.startDestinationId)) },
                interactionSource = NoRippleInteractionSource,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Podcasts,
                        contentDescription = "Explore"
                    )
                },
                label = { Text(text = "Explore") }
            )
        }
    }
}
