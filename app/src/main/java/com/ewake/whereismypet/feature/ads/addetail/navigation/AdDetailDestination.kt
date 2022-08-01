package com.ewake.whereismypet.feature.ads.addetail.navigation

import android.net.Uri
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ewake.whereismypet.core.navigation.NavigationDestination
import com.ewake.whereismypet.feature.ads.addetail.AdDetailScreen

/**
 * @author Nikolaevskiy Dmitriy
 */
object AdDetailDestination : NavigationDestination {
    const val adIdArg = "adId"
    private const val routePrefix = "ad_detail_route"

    override val route: String = "$routePrefix/{$adIdArg}"
    override val destination: String = "ad_detail_navigation"

    fun createNavigationRoute(adId: String): String {
        val encodedId = Uri.encode(adId)
        return "$routePrefix/$encodedId"
    }

    fun fromNavArgs(entry: NavBackStackEntry): String {
        val encodedId = entry.arguments?.getString(adIdArg)!!
        return Uri.decode(encodedId)
    }
}

@ExperimentalLifecycleComposeApi
fun NavGraphBuilder.adDetailDestination() {
    composable(
        route = AdDetailDestination.route,
        arguments = listOf(
            navArgument(AdDetailDestination.adIdArg) { type = NavType.StringType }
        )
    ) {
        AdDetailScreen()
    }
}