package com.ewake.whereismypet.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ewake.whereismypet.navigation.Screen

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */

@Composable
fun AdsListScreen(navController: NavController) {
    Text(text = "AdsList", modifier = Modifier.clickable {
        navController.navigate(Screen.AdDetail.route)
    })
}