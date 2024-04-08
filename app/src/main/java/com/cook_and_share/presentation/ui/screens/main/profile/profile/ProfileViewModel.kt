package com.cook_and_share.presentation.ui.screens.main.profile.profile

import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val storageRepository: StorageRepository,
    logRepository: LogRepository
): CookAndShareViewModel(logRepository){
    val userId = authRepository.currentUserId
    val userEmail = authRepository.currentUserEmail

    val recipes = storageRepository.myRecipes
    fun onRecipeLikeClick(recipe: Recipe) {
        launchCatching {
            storageRepository.update(recipe.copy(likes = recipe.likes + 1))
        }
    }
}