package com.ewake.whereismypet.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ewake.whereismypet.ui.navigation.Screen
import com.ewake.whereismypet.ui.screens.AdDetailScreen
import com.ewake.whereismypet.ui.screens.AdsListScreen
import com.ewake.whereismypet.ui.screens.ProfileScreen
import com.ewake.whereismypet.ui.viewmodel.AdsFeedViewModel

/**
 * @author Nikolaevskiy Dmitriy
 */

@Composable
fun NavHost(navController: NavHostController, innerPadding: PaddingValues) {
    androidx.navigation.compose.NavHost(
        navController,
        startDestination = Screen.AdsList.route,
        Modifier.padding(innerPadding)
    ) {
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.AdsList.route) {
            val viewModel = hiltViewModel<AdsFeedViewModel>()
            AdsListScreen(navController, viewModel)
        }
        composable(Screen.AdDetail.route) { AdDetailScreen() }
    }
}