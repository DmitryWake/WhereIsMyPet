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
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ewake.whereismypet.main.navigation.BottomBarDestination
import com.ewake.whereismypet.main.navigation.NavHost
import com.ewake.whereismypet.ui.theme.WhereMyPetTheme

/**
 * @author Nikolaevskiy Dmitriy
 */

@ExperimentalLifecycleComposeApi
@Composable
fun MainApp(appState: AppState = rememberAppState()) {
    WhereMyPetTheme {
        Scaffold(
            bottomBar = {
                if (appState.isBottomBarVisible) {
                    MainBottomBar(
                        navController = appState.navController,
                        bottomDestination = appState.bottomNavigationDestinations
                    )
                }
            }
        ) {
            NavHost(
                navController = appState.navController,
                onNavigate = appState::navigate,
                it
            )
        }
    }
}

@Composable
fun MainBottomBar(navController: NavHostController, bottomDestination: List<BottomBarDestination>) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomDestination.forEach { destination ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(stringResource(destination.titleRes)) },
                selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                onClick = {
                    navController.navigate(destination.route) {
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