package com.cook_and_share.di

import com.cook_and_share.data.repository.AuthRepositoryImpl
import com.cook_and_share.data.repository.LogRepositoryImpl
import com.cook_and_share.data.repository.StorageRepositoryImpl
import com.cook_and_share.data.repository.TranslateRepositoryImpl
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.domain.repository.TranslateRepository
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
    abstract fun provideLogRepository(impl: LogRepositoryImpl): LogRepository

    @Binds
    abstract fun provideStorageRepository(impl: StorageRepositoryImpl): StorageRepository

    @Binds
    abstract fun provideTranslateRepository(impl: TranslateRepositoryImpl): TranslateRepository
}