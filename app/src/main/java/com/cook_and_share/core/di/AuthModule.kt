package com.cook_and_share.core.di

import com.cook_and_share.core.data.repository.AuthRepositoryImpl
import com.cook_and_share.core.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {
    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}