package com.ewake.whereismypet.feature.profile.profilescreen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ewake.whereismypet.core.navigation.NavigationDestination
import com.ewake.whereismypet.feature.profile.ProfileScreen

/**
 * @author Nikolaevskiy Dmitriy
 */
object ProfileDestination : NavigationDestination {
    override val route: String = "profile_route"
    override val destination: String = "profile_destination"
    override val shouldShowBottomBar: Boolean = true
}

fun NavGraphBuilder.profileGraph() {
    composable(ProfileDestination.route) {
        ProfileScreen()
    }
}