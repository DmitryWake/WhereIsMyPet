package com.ewake.whereismypet.data.repository

/**
 * @author Nikolaevskiy Dmitriy
 */
interface AuthRepository {
   suspend fun sendLoginPhone(phone: String): String
}