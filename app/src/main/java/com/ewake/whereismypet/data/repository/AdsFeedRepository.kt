package com.ewake.whereismypet.data.repository

import com.ewake.whereismypet.core.model.AdModel

/**
 * @author Nikolaevskiy Dmitriy
 */
interface AdsFeedRepository {

    suspend fun getAdsFeed(page: Int): List<AdModel>
}