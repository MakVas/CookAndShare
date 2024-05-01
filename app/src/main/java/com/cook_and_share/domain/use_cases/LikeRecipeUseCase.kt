package com.cook_and_share.domain.use_cases

import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.StorageRepository
import javax.inject.Inject

class LikeRecipeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val storageRepository: StorageRepository
) {
    fun isRecipeLiked(recipe: Recipe): Boolean {
        return recipe.likes.contains(authRepository.currentUserId)
    }

    suspend fun onRecipeLikeClick(recipe: Recipe) {
        val profile = authRepository.getProfile(authRepository.currentUserId) ?: Profile()
        val isLiked = recipe.likes.contains(authRepository.currentUserId)
        val updatedRecipe =
            recipe.copy(
                likes = if (isLiked) recipe.likes - authRepository.currentUserId
                else recipe.likes + authRepository.currentUserId
            )
        val profileLiked =
            profile.copy(
                likedRecipes = if (isLiked) profile.likedRecipes - authRepository.currentUserId
                else profile.likedRecipes + authRepository.currentUserId
            )
        authRepository.updateProfile(profileLiked)
        storageRepository.update(updatedRecipe)
    }
}