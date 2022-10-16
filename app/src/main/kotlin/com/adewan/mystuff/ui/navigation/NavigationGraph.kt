@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.mystuff.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adewan.mystuff.core.repository.AuthenticationRepository
import com.adewan.mystuff.ui.composables.ThemedContainer
import com.adewan.mystuff.ui.gamedetails.GameDetailScreen
import com.adewan.mystuff.ui.home.HomeScreen
import com.adewan.mystuff.ui.library.LibraryScreen
import com.adewan.mystuff.ui.search.SearchScreen
import org.koin.androidx.compose.get

@Composable
fun NavigationGraph() {
    val navHostController = rememberNavController()
    val navigationDirector = NavigationDirector(navHostController = navHostController)
    val authenticationRepository: AuthenticationRepository = get()

    LaunchedEffect(key1 = authenticationRepository) {
        authenticationRepository.initializeIgdbAuthenticationToken()
    }

    ThemedContainer {
        Scaffold(
            bottomBar = { NavigationBottomBar(navHostController = navHostController) },
            topBar = { NavigationTopBar() }
        ) {
            NavHost(
                navController = navHostController,
                startDestination = NavDestination.Home.route,
                modifier = Modifier.padding(it)
            ) {
                composable(NavDestination.Home.route) {
                    HomeScreen(navigationDirector = navigationDirector)
                }
                composable(NavDestination.Library.route) {
                    LibraryScreen()
                }
                composable(NavDestination.Search.route) {
                    SearchScreen()
                }
                composable(
                    NavDestination.GameDetail.route,
                    arguments = listOf(navArgument("identifier") { type = NavType.StringType })
                ) { backStackEntry ->
                    val identifier = backStackEntry.arguments?.getString("identifier")
                        ?: throw IllegalStateException("Cannot launch detail screen with null identifier")
                    GameDetailScreen(
                        navigationDirector = navigationDirector,
                        identifier = identifier
                    )
                }
            }
        }
    }
}

@Composable
private fun NavigationTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "My Stuff",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
    )
}
