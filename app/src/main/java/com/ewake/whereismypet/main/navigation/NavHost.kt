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
import com.ewake.whereismypet.feature.profile.navigation.profileGraph

/**
 * @author Nikolaevskiy Dmitriy
 */

@ExperimentalLifecycleComposeApi
@Composable
fun NavHost(
    navController: NavHostController,
    onNavigate: (NavigationDestination, String?) -> Unit,
    innerPadding: PaddingValues,
    startDestination: String = AdsFeedDestination.route
) {
    androidx.navigation.compose.NavHost(
        navController,
        startDestination = startDestination,
        Modifier.padding(innerPadding)
    ) {
        profileGraph()
        adsFeedGraph(onDetailsNavigate = {
            onNavigate(AdDetailDestination, AdDetailDestination.createNavigationRoute(it))
        })
    }
}