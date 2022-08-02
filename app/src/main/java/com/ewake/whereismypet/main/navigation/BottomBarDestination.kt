package com.ewake.whereismypet.main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ewake.whereismypet.core.navigation.NavigationDestination

/**
 * @author Nikolaevskiy Dmitriy
 */
data class BottomBarDestination(
    override val route: String,
    override val destination: String,
    @DrawableRes val iconRes: Int? = null,
    @StringRes val titleRes: Int
) : NavigationDestination {
    override val shouldShowBottomBar: Boolean = true
}