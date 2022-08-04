package com.ewake.whereismypet.feature.ads.addetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.ewake.whereismypet.core.model.AdModel
import com.ewake.whereismypet.core.navigation.BackHandler
import com.ewake.whereismypet.feature.ads.addetail.viewmodel.AdDetailScreenUiState
import com.ewake.whereismypet.feature.ads.addetail.viewmodel.AdDetailViewModel
import com.ewake.whereismypet.feature.ads.addetail.viewmodel.AdModelUiState

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@ExperimentalLifecycleComposeApi
@Composable
fun AdDetailScreen(
    onBackPressed: () -> Unit,
    viewModel: AdDetailViewModel = hiltViewModel()
) {
    val uiState: AdDetailScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(onBack = onBackPressed)

    Scaffold(
        topBar = {
            AdDetailAppBar(onBackPressed)
        }
    ) {
        when (uiState.adModelUiState) {
            AdModelUiState.Error -> Text(text = "Ошибка")
            AdModelUiState.Loading -> CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            is AdModelUiState.Success -> AdDetailSuccess(
                model = (uiState.adModelUiState as AdModelUiState.Success).data,
                it
            )
        }
    }
}

@Composable
private fun AdDetailAppBar(onBackPressed: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Объявление")
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed.invoke() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 2.dp
    )
}

@Composable
private fun AdDetailSuccess(model: AdModel, paddingValues: PaddingValues) {
    Column(modifier = Modifier.padding(paddingValues)) {
        SubcomposeAsyncImage(
            model = model.iconUrl,
            contentDescription = model.title,
            loading = {
                CircularProgressIndicator()
            },
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        )

        Text(
            text = model.title,
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )
        Text(
            text = model.description,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
            color = Color.Gray
        )
    }
}