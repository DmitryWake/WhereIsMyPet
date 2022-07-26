package com.ewake.whereismypet.feature.ads.adsfeed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ewake.whereismypet.data.repository.AdsRepository
import com.ewake.whereismypet.feature.ads.adsfeed.paging.AdsFeedPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
@HiltViewModel
class AdsFeedViewModel @Inject constructor(
    private val repository: AdsRepository,
) : ViewModel() {
    val feedFlow = Pager(PagingConfig(pageSize = 6)) {
        AdsFeedPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}