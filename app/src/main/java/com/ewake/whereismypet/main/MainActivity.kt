package com.ewake.whereismypet.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.ewake.whereismypet.feature.auth.login.navigation.LoginDestination
import com.ewake.whereismypet.main.ui.MainApp
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalLifecycleComposeApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
        }

        setContent {
            MainApp(startNavigationDestination = LoginDestination)
        }
    }
}