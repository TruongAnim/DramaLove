package com.truonganim.drama.love.di

import com.truonganim.drama.love.data.repository.VideoRepositoryImpl
import com.truonganim.drama.love.data.repository.UserRepositoryImpl
import com.truonganim.drama.love.domain.repository.VideoRepository
import com.truonganim.drama.love.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVideoRepository(
        videoRepositoryImpl: VideoRepositoryImpl
    ): VideoRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
