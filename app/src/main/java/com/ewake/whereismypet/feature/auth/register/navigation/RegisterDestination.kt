package com.ewake.whereismypet.feature.auth.register.navigation

import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ewake.whereismypet.core.navigation.NavigationDestination
import com.ewake.whereismypet.feature.auth.register.RegisterScreen

/**
 * @author Nikolaevskiy Dmitriy
 */
object RegisterDestination : NavigationDestination {
    override val route: String = "register_route"
    override val destination: String = "register_destination"
    override val shouldShowBottomBar: Boolean = false
}

@ExperimentalLifecycleComposeApi
fun NavGraphBuilder.registerGraph(onNavigateNext: () -> Unit) {
    composable(route = RegisterDestination.route) {
        RegisterScreen(onNavigateNext)
    }
}