package com.cook_and_share.core.di

import com.google.firebase.firestore.FirebaseFirestore
import com.cook_and_share.core.domain.repository.FirestoreRepository
import com.cook_and_share.core.data.repository.FirestoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideFirestoreRepository(impl: FirestoreRepositoryImpl): FirestoreRepository = impl
}