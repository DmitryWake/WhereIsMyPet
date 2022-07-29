package com.ewake.whereismypet.data.repository.impl

import com.ewake.whereismypet.core.model.AdModel
import com.ewake.whereismypet.core.model.createTempAdsList
import com.ewake.whereismypet.data.repository.AdsFeedRepository
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
class TempAdsFeedRepository @Inject constructor() : AdsFeedRepository {

    override suspend fun getAdsFeed(page: Int): List<AdModel> {
        return createTempAdsList()
    }
}