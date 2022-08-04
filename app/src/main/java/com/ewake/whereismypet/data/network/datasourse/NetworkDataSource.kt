package com.ewake.whereismypet.data.network.datasourse

import com.ewake.whereismypet.data.network.model.NetworkAdDetailModel
import com.ewake.whereismypet.data.network.model.NetworkAdListModel
import com.ewake.whereismypet.data.network.model.NetworkResponse

/**
 * @author Nikolaevskiy Dmitriy
 */
interface NetworkDataSource {
    suspend fun getAdsList(page: Int): NetworkResponse<List<NetworkAdListModel>>
    suspend fun getAdDetail(adId: String): NetworkResponse<NetworkAdDetailModel>
    suspend fun getMobileConfirmSession(phoneNumber: String): NetworkResponse<String>
}