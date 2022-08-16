package com.ewake.whereismypet.data.repository.impl

import android.content.Context
import com.ewake.whereismypet.R
import com.ewake.whereismypet.core.model.AuthModel
import com.ewake.whereismypet.core.model.RegisterModel
import com.ewake.whereismypet.data.network.model.NetworkAuthModel
import com.ewake.whereismypet.data.network.model.NetworkResponse
import com.ewake.whereismypet.data.network.model.toAuthModel
import com.ewake.whereismypet.data.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
class FakeAuthRepository @Inject constructor(
    @ApplicationContext
    private val applicationContext: Context,
    private val networkJson: Json,
) : AuthRepository {
    @ExperimentalSerializationApi
    override suspend fun sendLoginPhone(phone: String): String {
        delay(1000)

        val response = networkJson.decodeFromStream<NetworkResponse<String>>(
            applicationContext.resources.openRawResource(
                R.raw.send_login_phone
            )
        )

        return requireNotNull(response.data)
    }

    @ExperimentalSerializationApi
    override suspend fun sendPhoneCode(credential: String, code: String): AuthModel {
        delay(1000)

        val response = networkJson.decodeFromStream<NetworkResponse<NetworkAuthModel>>(
            applicationContext.resources.openRawResource(R.raw.send_phone_code)
        )

        return requireNotNull(response.data?.toAuthModel())
    }

    override suspend fun register(registerModel: RegisterModel) {
        delay(1000)

        return
    }
}