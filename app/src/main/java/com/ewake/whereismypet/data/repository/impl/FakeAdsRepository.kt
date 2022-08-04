package com.ewake.whereismypet.data.repository.impl

import android.content.Context
import com.ewake.whereismypet.R
import com.ewake.whereismypet.core.model.AdModel
import com.ewake.whereismypet.data.network.model.NetworkAdListModel
import com.ewake.whereismypet.data.network.model.NetworkResponse
import com.ewake.whereismypet.data.network.model.toAdModel
import com.ewake.whereismypet.data.repository.AdsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
class FakeAdsRepository @Inject constructor(
    @ApplicationContext
    private val applicationContext: Context,
    private val networkJson: Json,
) : AdsRepository {

    @ExperimentalSerializationApi
    override suspend fun getAdsFeed(page: Int): List<AdModel> {
        delay(1000)

        val response = networkJson.decodeFromStream<NetworkResponse<List<NetworkAdListModel>>>(
            applicationContext.resources.openRawResource(
                R.raw.ads_list
            )
        )


        return response.data?.map { it.toAdModel() } ?: listOf()
    }

    @ExperimentalSerializationApi
    override fun getAd(id: String): Flow<AdModel?> {
        return flow {
            delay(500)
            val response = networkJson.decodeFromStream<NetworkResponse<List<NetworkAdListModel>>>(
                applicationContext.resources.openRawResource(
                    R.raw.ads_list
                )
            )

            this.emit(response.data?.firstOrNull { it.id == id }?.toAdModel())
        }
    }
}