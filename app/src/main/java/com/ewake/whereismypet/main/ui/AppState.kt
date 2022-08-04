package com.ewake.whereismypet.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ewake.whereismypet.R
import com.ewake.whereismypet.core.navigation.NavigationDestination
import com.ewake.whereismypet.feature.ads.adsfeed.navigation.AdsFeedDestination
import com.ewake.whereismypet.feature.profile.navigation.ProfileDestination
import com.ewake.whereismypet.main.navigation.BottomBarDestination

/**
 * @author Nikolaevskiy Dmitriy
 */
class AppState(val navController: NavHostController) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    var isBottomBarVisible: MutableState<Boolean> = mutableStateOf(true)

    val bottomNavigationDestinations = listOf(
        BottomBarDestination(
            route = AdsFeedDestination.route,
            destination = AdsFeedDestination.destination,
            iconRes = null,
            titleRes = R.string.app_name
        ),
        BottomBarDestination(
            route = ProfileDestination.route,
            destination = ProfileDestination.destination,
            iconRes = null,
            titleRes = R.string.app_name
        )
    )

    fun navigate(destination: NavigationDestination, route: String? = null) {
        if (destination is BottomBarDestination || bottomNavigationDestinations.firstOrNull { it.route == destination.route } != null) {
            navController.navigate(route ?: destination.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            navController.navigate(route ?: destination.route)
        }

        isBottomBarVisible.value = destination.shouldShowBottomBar
    }

    fun onBackPressed(shouldShowBottomBar: Boolean = true) {
        navController.popBackStack()
        isBottomBarVisible.value = shouldShowBottomBar
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