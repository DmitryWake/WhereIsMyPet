package com.ewake.whereismypet.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ewake.whereismypet.ui.navigation.Screen

/**
 * @author Nikolaevskiy Dmitriy
 */
class AppState(val navController: NavHostController) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    var isBottomBarVisible: Boolean = true

    companion object {
        val BOTTOM_NAVIGATION_ITEMS = listOf(Screen.AdsList, Screen.Profile)
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): AppState {
    return remember(navController) {
        AppState(navController)
    }
}