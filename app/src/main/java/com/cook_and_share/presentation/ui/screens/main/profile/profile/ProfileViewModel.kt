package com.cook_and_share.presentation.ui.screens.main.profile.profile

import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: AuthRepository,
    logRepository: LogRepository
): CookAndShareViewModel(logRepository){
    val userId = repository.currentUserId
    val userEmail = repository.currentUserEmail
}