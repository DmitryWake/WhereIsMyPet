package com.ewake.whereismypet.feature.login.navigation

import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ewake.whereismypet.core.navigation.NavigationDestination
import com.ewake.whereismypet.feature.login.LoginScreen

/**
 * @author Nikolaevskiy Dmitriy
 */
object LoginDestination : NavigationDestination {
    override val route: String = "login_route"
    override val destination: String = "login_destination"
    override val shouldShowBottomBar: Boolean = false
}

@ExperimentalLifecycleComposeApi
fun NavGraphBuilder.loginGraph(onNavigateNext: () -> Unit) {
    composable(route = LoginDestination.route) {
        LoginScreen(
            onNavigateNext
        )
    }
}