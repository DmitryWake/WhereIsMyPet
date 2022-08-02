package com.ewake.whereismypet.core.navigation

/**
 * @author Nikolaevskiy Dmitriy
 */
interface NavigationDestination {
    val route: String
    val destination: String
    val shouldShowBottomBar: Boolean
}