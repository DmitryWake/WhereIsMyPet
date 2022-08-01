package com.ewake.whereismypet.feature.ads.adsfeed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.SubcomposeAsyncImage
import com.ewake.whereismypet.core.model.AdModel
import com.ewake.whereismypet.feature.ads.adsfeed.viewmodel.AdsFeedViewModel

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */

@Composable
fun AdsFeedScreen(onDetailsNavigate: (String) -> Unit, viewModel: AdsFeedViewModel = hiltViewModel()) {

    val adsListItems = viewModel.feedFlow.collectAsLazyPagingItems()

    adsListItems.loadState.let { state ->
        when {
            state.refresh is LoadState.Loading || state.append is LoadState.Loading -> {
                CircularProgressIndicator()
            }
            state.append is LoadState.Error -> {
                Text(text = "Ошибка")
            }
        }
    }

    LazyColumn(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
        items(adsListItems) { model ->
            model?.let { adModel ->
                AdCard(model = adModel, onCardClickListener = { onDetailsNavigate.invoke(adModel.id) })
            }
        }
    }
}

@Composable
fun AdCard(model: AdModel, onCardClickListener: (model: AdModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
            .clickable { onCardClickListener.invoke(model) }
            .fillMaxWidth(), elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)) {

        Column {
            SubcomposeAsyncImage(
                model = model.iconUrl,
                contentDescription = model.title,
                loading = {
                    CircularProgressIndicator()
                },
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(200.dp)
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
}