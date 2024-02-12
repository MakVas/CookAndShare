package com.mealmentor.logic.database.sign_in

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    val auth: FirebaseAuth
): ViewModel() {
    val signedIn = mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val popupNotification = mutableStateOf(false)

    fun onSignUp(email: String, password: String) {
        inProgress.value = true

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    signedIn.value = true
                } else {
                    //   popupNotification.value = true
                }
                inProgress.value = false
            }
    }

    fun logIn(email: String, password: String) {
        inProgress.value = true

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    signedIn.value = true
                } else {
                    //   popupNotification.value = true
                }
                inProgress.value = false
            }
    }
}