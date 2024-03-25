package com.cook_and_share.core.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.cook_and_share.core.domain.model.Recipe
import com.cook_and_share.core.domain.model.User
import com.cook_and_share.core.presentation.util.Resource

interface FirestoreRepository {
    val currentUser: FirebaseUser?
    suspend fun updateUserInfo(name: String, email: String, imageUrl: String, bio: String): Resource<User>
    suspend fun getUserInfo(): User
    suspend fun getRecipe(): Recipe
    suspend fun createRecipe(
        title: String,
        imageUrl: String,
        tags: List<String>,
        ingredients: List<String>,
        recipe: String
        ): Resource<Void>
    suspend fun updateRecipe(recipe: Recipe): Resource<Recipe>
}