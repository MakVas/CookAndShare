package com.mealmentor.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
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
        try {
            firestore.collection(Constants.COLLECTION_NAME_USERS).get().await().map{
                val result = it.toObject(User::class.java)
                user = result
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return user
    }
}