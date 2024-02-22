package com.mealmentor.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.mealmentor.model.Recipe
import com.mealmentor.model.User
import com.mealmentor.util.Constants
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

    override suspend fun createRecipe(recipe: Recipe): Resource<Void> {
        return try {
            val result = firestore.collection(Constants.COLLECTION_NAME_RECIPES).document().set(recipe).await()
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