package com.ewake.whereismypet.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ewake.whereismypet.data.repository.AdsFeedRepository
import com.ewake.whereismypet.model.AdModel
import com.ewake.whereismypet.ui.paging.AdsFeedPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
@HiltViewModel
class AdsFeedViewModel @Inject constructor(
    private val repository: AdsFeedRepository
) : ViewModel() {
    val feedFlow = Pager(PagingConfig(pageSize = 6)) {
        AdsFeedPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}