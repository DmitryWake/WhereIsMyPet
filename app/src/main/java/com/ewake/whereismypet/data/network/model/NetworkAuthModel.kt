package com.ewake.whereismypet.data.network.model

import com.ewake.whereismypet.core.model.AuthModel
import kotlinx.serialization.Serializable

/**
 * @author Nikolaevskiy Dmitriy
 */
@Serializable
data class NetworkAuthModel(
    val sessionId: String,
    val userId: String,
    val isFirstAuth: Boolean
)

fun NetworkAuthModel.toAuthModel() = AuthModel(
    sessionId = sessionId,
    userId = userId,
    isFirstAuth = isFirstAuth
)
