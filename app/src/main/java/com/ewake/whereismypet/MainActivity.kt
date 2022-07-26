package com.ewake.whereismypet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ewake.whereismypet.navigation.Screen
import com.ewake.whereismypet.ui.screens.AdDetailScreen
import com.ewake.whereismypet.ui.screens.AdsListScreen
import com.ewake.whereismypet.ui.screens.ProfileScreen
import com.ewake.whereismypet.ui.theme.WhereMyPetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhereMyPetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    MainScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
private fun MainScreen(navController: NavHostController) {
    val bottomItems = listOf(Screen.AdsList, Screen.Profile)

    Scaffold(
        bottomBar = {
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
    ) { innerPadding ->
        NavHost(navController = navController, innerPadding = innerPadding)
    }
}

@Composable
private fun NavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController,
        startDestination = Screen.AdsList.route,
        Modifier.padding(innerPadding)
    ) {
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.AdsList.route) { AdsListScreen(navController) }
        composable(Screen.AdDetail.route) { AdDetailScreen() }
    }
}