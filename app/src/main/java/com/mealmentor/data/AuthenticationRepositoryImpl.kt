package com.mealmentor.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mealmentor.domain.model.User
import com.mealmentor.domain.repository.AuthenticationRepository
import com.mealmentor.util.Constants
import com.mealmentor.util.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthenticationRepository {
    private var operationSuccessful: Boolean = false
    override fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    @ExperimentalCoroutinesApi
    override fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {

                operationSuccessful = true
            }.await()

            emit(Response.Success(operationSuccessful))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unknown error occurred"))
        }
    }

    override fun firebaseSignOut(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unknown error occurred"))
        }
    }

    override fun firebaseSignUp(
        email: String,
        password: String,
        name: String
    ): Flow<Response<Boolean>> {
        operationSuccessful = false
        return flow {
            try {
                emit(Response.Loading)
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    operationSuccessful = true
                }.await()
                if (operationSuccessful) {
                    val userID = auth.currentUser?.uid!!
                    val obj = User(
                        name = name,
                        email = email,
                        userID = userID,
                        password = password
                    )
                    firestore.collection(Constants.COLLECTION_NAME_USERS).document(userID).set(obj)
                        .addOnSuccessListener {

                        }.await()
                    emit(Response.Success(operationSuccessful))
                } else {
                    Response.Success(operationSuccessful)
                }
            } catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "An unknown error occurred"))
            }
        }
    }
}
