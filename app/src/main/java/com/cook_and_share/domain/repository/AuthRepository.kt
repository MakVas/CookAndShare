package com.cook_and_share.domain.repository

import com.cook_and_share.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUserId: String
    val currentUserEmail: String
    val hasUser: Boolean
    val currentUser: Flow<User>
    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createAccount(email: String, password: String)
    suspend fun deleteAccount()
    suspend fun signOut()
}