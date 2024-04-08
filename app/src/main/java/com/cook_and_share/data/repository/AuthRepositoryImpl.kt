package com.cook_and_share.data.repository

import com.cook_and_share.domain.model.User
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.presentation.util.Constants.CREATE_ACCOUNT_TRACE
import com.cook_and_share.presentation.util.trace
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val currentUserEmail: String
        get() = auth.currentUser?.email.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let {
                        User(it.uid)
                    } ?: User())
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }
    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun createAccount(email: String, password: String){
        trace(CREATE_ACCOUNT_TRACE) {
            auth.createUserWithEmailAndPassword(email, password).await()
        }
    }
    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}