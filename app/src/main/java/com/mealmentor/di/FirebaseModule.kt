package com.mealmentor.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.mealmentor.data.AuthenticationRepositoryImpl
import com.mealmentor.domain.repository.AuthenticationRepository
import com.mealmentor.domain.use_cases.AuthenticationUseCases
import com.mealmentor.domain.use_cases.FirebaseAuthState
import com.mealmentor.domain.use_cases.FirebaseSignIn
import com.mealmentor.domain.use_cases.FirebaseSignOut
import com.mealmentor.domain.use_cases.FirebaseSignUp
import com.mealmentor.domain.use_cases.IsUserAuthenticated
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseAuthentication(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth = auth, firestore = firestore)
    }


    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthenticationRepository) = AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository = repository),
        firebaseAuthState = FirebaseAuthState(repository = repository),
        firebaseSignOut = FirebaseSignOut(repository = repository),
        firebaseSignIn = FirebaseSignIn(repository = repository),
        firebaseSignUp = FirebaseSignUp(repository = repository)
    )
}