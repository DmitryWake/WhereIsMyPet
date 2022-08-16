package com.ewake.whereismypet.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavHostController
import com.ewake.whereismypet.core.navigation.NavigationDestination
import com.ewake.whereismypet.feature.ads.addetail.navigation.AdDetailDestination
import com.ewake.whereismypet.feature.ads.adsfeed.navigation.AdsFeedDestination
import com.ewake.whereismypet.feature.ads.adsfeed.navigation.adsFeedGraph
import com.ewake.whereismypet.feature.auth.login.navigation.loginGraph
import com.ewake.whereismypet.feature.auth.register.navigation.RegisterDestination
import com.ewake.whereismypet.feature.auth.register.navigation.registerGraph
import com.ewake.whereismypet.feature.profile.profilescreen.navigation.profileGraph

/**
 * @author Nikolaevskiy Dmitriy
 */

@ExperimentalLifecycleComposeApi
@Composable
fun NavHost(
    navController: NavHostController,
    onNavigate: (NavigationDestination, String?) -> Unit,
    onBackPressed: (Boolean) -> Unit,
    innerPadding: PaddingValues,
    startDestination: NavigationDestination
) {
    androidx.navigation.compose.NavHost(
        navController,
        startDestination = startDestination.route,
        Modifier.padding(innerPadding)
    ) {
        loginGraph(
            onNavigateNext = { isFirstLogin ->
                if (isFirstLogin) {
                    onNavigate(RegisterDestination, null)
                } else {
                    onNavigate(AdsFeedDestination, null)
                }
            },
            onRegisterNavigate = {
                onNavigate(AdsFeedDestination, null)
            }
        )
        profileGraph()
        adsFeedGraph(
            onDetailsNavigate = {
                onNavigate(AdDetailDestination, AdDetailDestination.createNavigationRoute(it))
            },
            onAdDetailBackPressed = {
                onBackPressed(AdsFeedDestination.shouldShowBottomBar)
            }
        )
    }
}