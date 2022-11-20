package com.adewan.mystuff.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.adewan.mystuff.feature.explore.navigation.exploreGraph
import com.adewan.mystuff.feature.explore.navigation.exploreRoute
import com.adewan.mystuff.feature.explore.navigation.navigateToExploreRoute
import com.adewan.mystuff.features.landing.navigation.landingRoute
import com.adewan.mystuff.features.landing.navigation.landingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = exploreRoute
) {
    Scaffold {
        NavHost(
            navController = navController,
            modifier = modifier.padding(it),
            startDestination = startDestination
        ) {
            landingScreen(
                navigateToExplore = {
                    navController.navigateToExploreRoute(
                        navOptions = navOptions {
                            popUpTo(landingRoute) {
                                inclusive = true
                            }
                        }
                    )
                }
            )
            exploreGraph { }
        }
    }
}
