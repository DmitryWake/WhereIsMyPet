package com.ewake.whereismypet.data.repository

import com.ewake.whereismypet.core.model.AuthModel

/**
 * @author Nikolaevskiy Dmitriy
 */
interface AuthRepository {
   suspend fun sendLoginPhone(phone: String): String
   suspend fun sendPhoneCode(credential: String, code: String): AuthModel
}