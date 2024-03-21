package com.cook_and_share.data

import com.google.firebase.auth.FirebaseUser
import com.cook_and_share.model.Recipe
import com.cook_and_share.model.User
import com.cook_and_share.util.Resource

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