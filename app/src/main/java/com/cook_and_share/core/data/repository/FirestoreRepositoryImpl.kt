package com.cook_and_share.core.data.repository

import com.cook_and_share.core.domain.repository.FirestoreRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.cook_and_share.core.domain.model.Recipe
import com.cook_and_share.core.domain.model.User
import com.cook_and_share.core.presentation.util.Constants
import com.cook_and_share.core.presentation.util.Resource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): FirestoreRepository {
    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override suspend fun updateUserInfo(
        name: String,
        email: String,
        imageUrl: String,
        bio: String
    ): Resource<User> {

        return Resource.Success(User())
    }

    override suspend fun getUserInfo(): User {
        var user = User()
        val userID = currentUser!!.uid
        try {
            try {
                val document = firestore.collection(Constants.COLLECTION_NAME_USERS).document(userID).get().await()
                val result = document.toObject(User::class.java)
                if (result != null) {
                    user = result
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return user
    }

    override suspend fun getRecipe(): Recipe {
        TODO("Not yet implemented")
    }

    override suspend fun createRecipe(
        title: String,
        imageUrl: String,
        tags: List<String>,
        ingredients: List<String>,
        recipe: String
    ): Resource<Void> {
        return try {
            val userID = currentUser!!.uid
            val document = firestore.collection(Constants.COLLECTION_NAME_USERS).document(userID).get().await()
            val author = document.getString("name")
            val recipeUnit = Recipe(
                userID = currentUser!!.uid,
                author = author!!,
                title = title,
                imageUrl = imageUrl,
                tags = tags,
                ingredients = ingredients,
                recipe = recipe
            )
            val result = firestore.collection(Constants.COLLECTION_NAME_UNVERIFIED_RECIPES).document().set(recipeUnit).await()
            Resource.Success(result)
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e)
        }
    }

    override suspend fun updateRecipe(recipe: Recipe): Resource<Recipe> {
        TODO("Not yet implemented")
    }
}