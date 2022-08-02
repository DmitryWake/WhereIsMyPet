package com.ewake.whereismypet.main.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.ewake.whereismypet.main.ui.MainApp
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalLifecycleComposeApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}