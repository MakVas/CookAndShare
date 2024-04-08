package com.cook_and_share.domain.repository

import com.cook_and_share.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    val recipes: Flow<List<Recipe>>
    suspend fun getRecipe(recipeId: String): Recipe?
    suspend fun save(recipe: Recipe): String
    suspend fun update(recipe: Recipe)
    suspend fun delete(recipeId: String)
}