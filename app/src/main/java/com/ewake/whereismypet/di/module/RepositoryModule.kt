package com.ewake.whereismypet.di.module

import com.ewake.whereismypet.data.repository.AdsFeedRepository
import com.ewake.whereismypet.data.repository.impl.TempAdsFeedRepository
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
    abstract fun bindAdsFeedRepository(impl: TempAdsFeedRepository): AdsFeedRepository
}