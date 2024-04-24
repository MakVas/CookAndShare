package com.cook_and_share.presentation.ui.screens.main.home

import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    logRepository: LogRepository,
    private val storageRepository: StorageRepository,
    private val authRepository: AuthRepository
) : CookAndShareViewModel(logRepository) {

    val recipes = storageRepository.recipes
    val dailyRecipes = storageRepository.dailyRecipes

    fun onRecipeLikeClick(recipe: Recipe) {
        launchCatching {
            val profile = authRepository.getProfile(authRepository.currentUserId) ?: Profile()
            if (
                profile.likedRecipes.contains(recipe.id).not()
            ) {
                val recipeLiked = recipe.copy(likes = recipe.likes + 1)
                storageRepository.update(recipeLiked)
                val profileLiked = profile.copy(likedRecipes = profile.likedRecipes + recipe.id)
                authRepository.updateProfile(profileLiked)
            } else {
                val recipeLiked = recipe.copy(likes = recipe.likes - 1)
                storageRepository.update(recipeLiked)
                val profileLiked = profile.copy(likedRecipes = profile.likedRecipes - recipe.id)
                authRepository.updateProfile(profileLiked)
            }
        }
    }
}
