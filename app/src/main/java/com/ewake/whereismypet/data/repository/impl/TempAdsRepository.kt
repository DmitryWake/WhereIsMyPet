package com.ewake.whereismypet.data.repository.impl

import com.ewake.whereismypet.core.model.AdModel
import com.ewake.whereismypet.core.model.createTempAdsList
import com.ewake.whereismypet.data.repository.AdsRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Nikolaevskiy Dmitriy
 */
class TempAdsRepository @Inject constructor() : AdsRepository {

    override suspend fun getAdsFeed(page: Int): List<AdModel> {
        delay(500)
        return createTempAdsList()
    }

    override fun getAd(id: String): Flow<AdModel> {
        return flow {
            delay(500)
            this.emit(createTempAdsList().first { it.id == id })
        }
    }
}