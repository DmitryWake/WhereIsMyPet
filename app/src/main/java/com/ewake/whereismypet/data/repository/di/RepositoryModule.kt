package com.ewake.whereismypet.data.repository.di

import com.ewake.whereismypet.data.repository.AdsRepository
import com.ewake.whereismypet.data.repository.AuthRepository
import com.ewake.whereismypet.data.repository.impl.FakeAdsRepository
import com.ewake.whereismypet.data.repository.impl.FakeAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Nikolaevskiy Dmitriy
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAdsFeedRepository(impl: FakeAdsRepository): AdsRepository

    @Binds
    abstract fun bindAuthFeedRepository(impl: FakeAuthRepository): AuthRepository
}