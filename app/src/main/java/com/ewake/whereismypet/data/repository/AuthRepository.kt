package com.ewake.whereismypet.data.repository

import com.ewake.whereismypet.core.model.AuthModel
import com.ewake.whereismypet.core.model.RegisterModel

/**
 * @author Nikolaevskiy Dmitriy
 */
interface AuthRepository {
   suspend fun sendLoginPhone(phone: String): String
   suspend fun sendPhoneCode(credential: String, code: String): AuthModel
   suspend fun register(registerModel: RegisterModel)
}