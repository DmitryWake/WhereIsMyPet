package com.ewake.whereismypet.feature.ads.addetail.navigation

import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ewake.whereismypet.core.navigation.NavigationDestination
import com.ewake.whereismypet.feature.ads.addetail.AdDetailScreen

/**
 * @author Nikolaevskiy Dmitriy
 */
object AdDetailDestination : NavigationDestination {
    override val route: String = "ad_detail_route"
    override val destination: String = "ad_detail_navigation"
}

@ExperimentalLifecycleComposeApi
fun NavGraphBuilder.adDetailDestination() {
    composable(AdDetailDestination.route) {
        AdDetailScreen()
    }
}