package com.ewake.whereismypet.data.network.model

import kotlinx.serialization.Serializable

/**
 * @author Nikolaevskiy Dmitriy
 */

@Serializable
data class NetworkResponse<T>(
    val code: String,
    val data: T? = null,
    val message: String? = null
)