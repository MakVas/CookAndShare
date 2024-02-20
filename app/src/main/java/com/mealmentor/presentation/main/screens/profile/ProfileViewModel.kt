package com.mealmentor.presentation.main.screens.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mealmentor.data.FirestoreRepository
import com.mealmentor.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: FirestoreRepository
): ViewModel(){
    private val currentUser: FirebaseUser?
        get() = repository.currentUser

    val user = mutableStateOf(User())

    init{
        if(currentUser != null){
            getUserInfo()
        }
    }
    private fun getUserInfo() = viewModelScope.launch {
        user.value = repository.getUserInfo()
    }
}