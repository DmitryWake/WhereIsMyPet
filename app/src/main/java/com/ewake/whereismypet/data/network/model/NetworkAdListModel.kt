package com.ewake.whereismypet.data.network.model

import com.ewake.whereismypet.core.model.AdModel
import kotlinx.serialization.Serializable

/**
 * @author Nikolaevskiy Dmitriy
 */
@Serializable
data class NetworkAdListModel(
    val id: String,
    val title: String,
    val description: String,
    val iconUrl: String
)

fun NetworkAdListModel.toAdModel() = AdModel(
    id = id,
    title = title,
    description = description,
    iconUrl = iconUrl
)