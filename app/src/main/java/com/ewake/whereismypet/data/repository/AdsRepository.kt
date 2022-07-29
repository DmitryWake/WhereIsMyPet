package com.ewake.whereismypet.data.repository

import com.ewake.whereismypet.core.model.AdModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Nikolaevskiy Dmitriy
 */
interface AdsRepository {

    suspend fun getAdsFeed(page: Int): List<AdModel>
    fun getAd(id: String): Flow<AdModel>
}