package com.cook_and_share.domain.repository

import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    val recipes: Flow<List<Recipe>>
    val dailyRecipes: Flow<List<Recipe>>
    val myRecipes: Flow<List<Recipe>>
    suspend fun searchProfiles(query: String, fieldName: String): Flow<List<Profile>>
    suspend fun searchRecipes(query: String, fieldName: String): Flow<List<Recipe>>
    suspend fun getRecipe(recipeId: String): Recipe?
    suspend fun save(recipe: Recipe)
    suspend fun update(recipe: Recipe)
    suspend fun delete(recipeId: String)
}