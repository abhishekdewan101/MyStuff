@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.mystuff.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.adewan.mystuff.feature.explore.navigation.exploreGraph
import com.adewan.mystuff.features.landing.navigation.landingRoute
import com.adewan.mystuff.features.landing.navigation.landingScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = landingRoute
) {
    Scaffold {
        NavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            startDestination = startDestination
        ) {
            landingScreen()
            exploreGraph { }
        }
    }
}
