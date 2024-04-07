package com.cook_and_share.di

import com.cook_and_share.data.repository.AuthRepositoryImpl
import com.cook_and_share.data.repository.LogRepositoryImpl
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository
    @Binds
    abstract fun provideLogService(impl: LogRepositoryImpl): LogRepository
}