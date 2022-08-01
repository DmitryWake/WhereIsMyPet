package com.ewake.whereismypet.feature.ads.adsfeed.navigation

import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ewake.whereismypet.core.navigation.NavigationDestination
import com.ewake.whereismypet.feature.ads.addetail.navigation.adDetailDestination
import com.ewake.whereismypet.feature.ads.adsfeed.AdsFeedScreen

/**
 * @author Nikolaevskiy Dmitriy
 */
object AdsFeedDestination : NavigationDestination {
    override val route: String = "ads_feed_route"
    override val destination: String = "ads_feed_destination"
}

@ExperimentalLifecycleComposeApi
fun NavGraphBuilder.adsFeedGraph(onDetailsNavigate: (String) -> Unit) {
    navigation(startDestination = AdsFeedDestination.destination, route = AdsFeedDestination.route) {
        composable(AdsFeedDestination.destination) {
            AdsFeedScreen(onDetailsNavigate)
        }
        adDetailDestination()
    }
}