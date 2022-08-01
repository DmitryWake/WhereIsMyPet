package com.ewake.whereismypet.feature.ads.addetail

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ewake.whereismypet.feature.ads.addetail.viewmodel.AdDetailScreenUiState
import com.ewake.whereismypet.feature.ads.addetail.viewmodel.AdDetailViewModel
import com.ewake.whereismypet.feature.ads.addetail.viewmodel.AdModelUiState

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@ExperimentalLifecycleComposeApi
@Composable
fun AdDetailScreen(viewModel: AdDetailViewModel = hiltViewModel()) {
    val uiState: AdDetailScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.adModelUiState) {
        AdModelUiState.Error -> Text(text = "Ошибка")
        AdModelUiState.Loading -> CircularProgressIndicator()
        is AdModelUiState.Success -> Text(text = (uiState.adModelUiState as AdModelUiState.Success).data.id)
    }
}