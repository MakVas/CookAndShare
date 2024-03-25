package com.cook_and_share.core.domain.repository

import com.cook_and_share.core.presentation.util.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(email: String, password: String, name: String): Resource<FirebaseUser>
    fun logout()
}