package com.ewake.whereismypet.feature.auth.login.navigation

import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ewake.whereismypet.core.navigation.NavigationDestination
import com.ewake.whereismypet.feature.auth.login.LoginScreen
import com.ewake.whereismypet.feature.auth.register.navigation.registerGraph

/**
 * @author Nikolaevskiy Dmitriy
 */
object LoginDestination : NavigationDestination {
    override val route: String = "login_route"
    override val destination: String = "login_destination"
    override val shouldShowBottomBar: Boolean = false
}

@ExperimentalLifecycleComposeApi
fun NavGraphBuilder.loginGraph(onNavigateNext: (Boolean) -> Unit, onRegisterNavigate: () -> Unit) {
    navigation(route = LoginDestination.route, startDestination = LoginDestination.destination) {
        composable(route = LoginDestination.destination) { LoginScreen(onNavigateNext) }
        registerGraph(onRegisterNavigate)
    }
}