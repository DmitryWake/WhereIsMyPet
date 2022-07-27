package com.ewake.whereismypet.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ewake.whereismypet.data.repository.AdsFeedRepository
import com.ewake.whereismypet.model.AdModel

/**
 * @author Nikolaevskiy Dmitriy
 */
class AdsFeedPagingSource(private val repository: AdsFeedRepository) : PagingSource<Int, AdModel>() {
    override fun getRefreshKey(state: PagingState<Int, AdModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AdModel> {
        val nextPage = params.key ?: 1

        val list = repository.getAdsFeed(nextPage)

        return LoadResult.Page(
            data = list,
            prevKey = if (nextPage == 1) null else nextPage - 1,
            nextKey = if (list.isEmpty()) null else nextPage + 1
        )
    }
}