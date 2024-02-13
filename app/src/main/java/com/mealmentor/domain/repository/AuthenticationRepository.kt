package com.mealmentor.domain.repository

import com.mealmentor.util.Response
import kotlinx.coroutines.flow.Flow

interface  AuthenticationRepository {
    fun isUserAuthenticatedInFirebase(): Boolean
    fun getFirebaseAuthState(): Flow<Boolean>
    fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>>
    fun firebaseSignOut(): Flow<Response<Boolean>>
    fun firebaseSignUp(email: String, password: String, name: String): Flow<Response<Boolean>>
}