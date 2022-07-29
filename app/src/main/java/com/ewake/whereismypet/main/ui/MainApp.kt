package com.ewake.whereismypet.main.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ewake.whereismypet.main.navigation.NavHost
import com.ewake.whereismypet.main.ui.AppState.Companion.BOTTOM_NAVIGATION_ITEMS
import com.ewake.whereismypet.ui.navigation.Screen
import com.ewake.whereismypet.ui.theme.WhereMyPetTheme

/**
 * @author Nikolaevskiy Dmitriy
 */

@Composable
fun MainApp(appState: AppState = rememberAppState()) {
    WhereMyPetTheme {
        Scaffold(
            bottomBar = {
                if (appState.isBottomBarVisible) {
                    MainBottomBar(
                        navController = appState.navController,
                        bottomItems = BOTTOM_NAVIGATION_ITEMS
                    )
                }
            }
        ) {
            NavHost(navController = appState.navController, it)
        }
    }
}

@Composable
fun MainBottomBar(navController: NavHostController, bottomItems: List<Screen>) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomItems.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}