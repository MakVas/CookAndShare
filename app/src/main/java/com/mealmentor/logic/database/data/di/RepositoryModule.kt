package com.mealmentor.logic.database.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.mealmentor.logic.database.data.repository.RecipeRepository
import com.mealmentor.logic.database.data.repository.RecipeRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRecipeRepository(
        database: FirebaseFirestore
    ): RecipeRepository {
        return RecipeRepositoryImp(database)
    }
}