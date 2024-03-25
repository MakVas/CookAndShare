package com.cook_and_share.profile.presentation.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.cook_and_share.core.domain.repository.FirestoreRepository
import com.cook_and_share.core.domain.model.User
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