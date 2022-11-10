@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
)

package com.adewan.mystuff.ui.navigation

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adewan.mystuff.core.data.repositories.AuthenticationRepository
import com.adewan.mystuff.ui.composables.ExpandedImageViewer
import com.adewan.mystuff.ui.composables.ThemedContainer
import com.adewan.mystuff.ui.gamedetails.GameDetailScreen
import com.adewan.mystuff.ui.home.HomeScreen
import com.adewan.mystuff.ui.library.LibraryScreen
import com.adewan.mystuff.ui.moviedetails.MovieDetailScreen
import com.adewan.mystuff.ui.search.SearchScreen
import com.adewan.mystuff.ui.splash.SplashScreen
import com.adewan.mystuff.ui.tvshowdetails.TvShowDetailScreen
import org.koin.androidx.compose.get

@Composable
fun NavigationGraph() {
    val navHostController = rememberNavController()
    val navigationDirector = NavigationDirectorImpl(navHostController = navHostController)
    val authenticationRepository: AuthenticationRepository = get()
    var showBottombar by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = authenticationRepository) {
        authenticationRepository.getAndSaveAuthenticationToken()
    }

    ThemedContainer {
        Scaffold(
            bottomBar = {
                // FIXME: Found an issue where the page was showing before the bottom bar hides slightly after the page was shown causing a shift.
                AnimatedVisibility(
                    visible = showBottombar,
                    enter = slideInVertically { it } + fadeIn(),
                    exit = slideOutVertically { it } + fadeOut(),
                ) {
                    NavigationBottomBar(navHostController = navHostController)
                }
            },
            modifier = Modifier.navigationBarsPadding(),
//            topBar = { NavigationTopBar() }
        ) {
            NavHost(
                navController = navHostController,
                startDestination = NavDestination.Splash.route,
                modifier = Modifier
                    .padding(it),
            ) {
                composable(NavDestination.Splash.route) {
                    showBottombar = NavDestination.Splash.showBottomBar
                    SplashScreen(navigationDirector = navigationDirector)
                }
                composable(NavDestination.Home.route) {
                    showBottombar = NavDestination.Home.showBottomBar
                    HomeScreen(navigationDirector = navigationDirector)
                }
                composable(NavDestination.Library.route) {
                    showBottombar = NavDestination.Library.showBottomBar
                    LibraryScreen()
                }
                composable(NavDestination.Search.route) {
                    showBottombar = NavDestination.Search.showBottomBar
                    SearchScreen()
                }
                composable(
                    NavDestination.GameDetail.route,
                    arguments = listOf(navArgument("identifier") { type = NavType.StringType }),
                ) { backStackEntry ->
                    showBottombar = NavDestination.GameDetail.showBottomBar
                    val identifier = backStackEntry.arguments?.getString("identifier")
                        ?: throw IllegalStateException("Cannot launch detail screen with null identifier")
                    GameDetailScreen(
                        navigationDirector = navigationDirector,
                        identifier = identifier,
                    )
                }

                composable(
                    NavDestination.MovieDetail.route,
                    arguments = listOf(navArgument("identifier") { type = NavType.StringType }),
                ) { backStackEntry ->
                    showBottombar = NavDestination.MovieDetail.showBottomBar
                    val identifier = backStackEntry.arguments?.getString("identifier")
                        ?: throw IllegalStateException("Cannot launch detail screen with null identifier")
                    MovieDetailScreen(
                        navigationDirector = navigationDirector,
                        identifier = identifier,
                    )
                }

                composable(
                    NavDestination.TvShowDetail.route,
                    arguments = listOf(navArgument("identifier") { type = NavType.StringType }),
                ) { backStackEntry ->
                    showBottombar = NavDestination.TvShowDetail.showBottomBar
                    val identifier = backStackEntry.arguments?.getString("identifier")
                        ?: throw IllegalStateException("Cannot launch detail screen with null identifier")
                    TvShowDetailScreen(
                        navigationDirector = navigationDirector,
                        identifier = identifier,
                    )
                }

                composable(
                    NavDestination.ExpandedImageViewer.route,
                    arguments = listOf(navArgument("url") { type = NavType.StringType }),
                ) { backStackEntry ->
                    showBottombar = NavDestination.ExpandedImageViewer.showBottomBar
                    val url = backStackEntry.arguments?.getString("url")
                        ?: throw IllegalStateException("Cannot launch detail screen with null identifier")
                    ExpandedImageViewer(url = Uri.decode(url))
                }
            }
        }
    }
}
