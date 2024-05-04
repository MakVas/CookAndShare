package com.cook_and_share.data.repository

import android.net.Uri
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.User
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.presentation.util.Constants.COLLECTION_NAME_USERS
import com.cook_and_share.presentation.util.Constants.CREATE_ACCOUNT_TRACE
import com.cook_and_share.presentation.util.trace
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage
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

    override suspend fun uploadProfileImage(uri: Uri?): String {
        var imgUrl = ""
        uri?.let {
            val uploadTask =
                storage.reference.child(COLLECTION_NAME_USERS + "/${currentUserId}").putFile(it)
                    .await()
            imgUrl = uploadTask.storage.downloadUrl.await().toString()
        }
        return imgUrl
    }

    override suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun createAccount(email: String, password: String) {
        trace(CREATE_ACCOUNT_TRACE) {
            auth.createUserWithEmailAndPassword(email, password).await()
        }
    }

    override suspend fun getProfile(profileID: String): Profile? =
        database.reference.child(COLLECTION_NAME_USERS).child(profileID).get().await()
            .getValue<Profile>()

    override suspend fun saveProfile(profile: Profile) {
        val updatedProfile = profile.copy(userID = currentUserId)
        database.reference.child(COLLECTION_NAME_USERS).child(currentUserId)
            .setValue(updatedProfile).await()
    }

    override suspend fun updateProfile(profile: Profile) {
        database.reference.child(COLLECTION_NAME_USERS).child(profile.userID).setValue(profile)
            .await()
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}