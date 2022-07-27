package com.ewake.whereismypet.data.repository.impl

import com.ewake.whereismypet.data.repository.AdsFeedRepository
import com.ewake.whereismypet.model.AdModel
import com.ewake.whereismypet.utils.createTempAdsList
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
class TempAdsFeedRepository @Inject constructor() : AdsFeedRepository {

    override suspend fun getAdsFeed(page: Int): List<AdModel> {
        return createTempAdsList()
    }
}