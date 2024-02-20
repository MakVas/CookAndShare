package com.mealmentor.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mealmentor.data.AuthRepository
import com.mealmentor.data.AuthRepositoryImpl
import com.mealmentor.data.FirestoreRepository
import com.mealmentor.data.FirestoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Singleton
    @Provides
    fun provideFirestoreRepository(impl: FirestoreRepositoryImpl): FirestoreRepository = impl
}