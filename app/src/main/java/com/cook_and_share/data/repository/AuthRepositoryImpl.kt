package com.cook_and_share.data.repository

import android.util.Log
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.User
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.presentation.util.Constants.COLLECTION_NAME_USERS
import com.cook_and_share.presentation.util.Constants.CREATE_ACCOUNT_TRACE
import com.cook_and_share.presentation.util.Constants.SAVE_PROFILE_TRACE
import com.cook_and_share.presentation.util.Constants.UPDATE_PROFILE_TRACE
import com.cook_and_share.presentation.util.trace
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : AuthRepository {
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

    override suspend fun createAccount(email: String, password: String) {
        trace(CREATE_ACCOUNT_TRACE) {
            Log.wtf("LOG", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
            auth.createUserWithEmailAndPassword(email, password).await()
            Log.wtf("LOG", "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC")
        }
    }

    override suspend fun getProfile(profileID: String): Profile? =
        firestore.collection(COLLECTION_NAME_USERS).document(profileID).get().await().toObject()

    override suspend fun saveProfile(profile: Profile): String =
        trace(SAVE_PROFILE_TRACE) {
            Log.wtf("LOG", "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB")
            val updatedProfile = profile.copy(userID = currentUserId)
            firestore.collection(COLLECTION_NAME_USERS).add(updatedProfile).await().id
        }

    override suspend fun updateProfile(profile: Profile): Unit =
        trace(UPDATE_PROFILE_TRACE) {
            firestore.collection(COLLECTION_NAME_USERS).document(profile.userID).set(profile).await()
        }


    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}