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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.adewan.mystuff.common.ux.NoRippleInteractionSource
import com.adewan.mystuff.feature.explore.exploreView
import com.adewan.mystuff.feature.explore.navigateToExploreView
import com.adewan.mystuff.feature.library.libraryRoute
import com.adewan.mystuff.feature.library.libraryView
import com.adewan.mystuff.feature.library.navigateToLibraryView
import com.adewan.mystuff.feature.search.navigateToSearchView
import com.adewan.mystuff.feature.search.searchView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MsApp(modifier: Modifier = Modifier) {
    var bottomBarPresent by remember { mutableStateOf(true) }
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
                startDestination = libraryRoute
            ) {
                searchView(updateBottomBar = { showBottomBar -> bottomBarPresent = showBottomBar })
                libraryView(updateBottomBar = { showBottomBar -> bottomBarPresent = showBottomBar })
                exploreView(updateBottomBar = { showBottomBar -> bottomBarPresent = showBottomBar })
            }
        }
    }
}

@Composable
internal fun MsAppBottomBar(navController: NavController, bottomBarPresent: Boolean) {
    var selectedIndex by rememberSaveable { mutableStateOf(1) }
    AnimatedVisibility(
        visible = bottomBarPresent,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
    ) {
        NavigationBar(modifier = Modifier.fillMaxWidth(), containerColor = Color.Transparent) {
            NavigationBarItem(
                selected = selectedIndex == 0,
                onClick = {
                    selectedIndex = 0
                    navController.navigateToSearchView()
                },
                interactionSource = NoRippleInteractionSource,
                icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
                label = { Text(text = "Search") }
            )
            NavigationBarItem(
                selected = selectedIndex == 1,
                onClick = {
                    selectedIndex = 1
                    navController.navigateToLibraryView()
                },
                interactionSource = NoRippleInteractionSource,
                icon = { Icon(imageVector = Icons.Default.Apps, contentDescription = "MyStuff") },
                label = { Text(text = "MyStuff") }
            )
            NavigationBarItem(
                selected = selectedIndex == 2,
                onClick = {
                    selectedIndex = 2
                    navController.navigateToExploreView()
                },
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
