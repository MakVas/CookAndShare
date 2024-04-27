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


    suspend fun onRecipeLikeClick(recipe: Recipe) {
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