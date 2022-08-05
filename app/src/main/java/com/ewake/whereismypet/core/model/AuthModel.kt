package com.ewake.whereismypet.core.model

/**
 * @author Nikolaevskiy Dmitriy
 */
data class AuthModel(
    val sessionId: String,
    val userId: String,
    val isFirstAuth: Boolean
)