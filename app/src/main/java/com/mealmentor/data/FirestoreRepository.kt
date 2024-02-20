package com.mealmentor.data

import com.google.firebase.auth.FirebaseUser
import com.mealmentor.model.User

interface FirestoreRepository {
    val currentUser: FirebaseUser?
    suspend fun updateUserInfo(name: String, email: String, imageUrl: String, bio: String): Resource<User>
    suspend fun getUserInfo(): User
}