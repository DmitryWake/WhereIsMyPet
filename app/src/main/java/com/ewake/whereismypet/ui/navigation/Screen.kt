package com.ewake.whereismypet.ui.navigation

import androidx.annotation.StringRes
import com.ewake.whereismypet.R

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Profile : Screen("profile", R.string.app_name)
    object AdsList : Screen("adslist", R.string.app_name)
    object AdDetail: Screen("adDetail", R.string.app_name)
}