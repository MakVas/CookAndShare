package com.cook_and_share.presentation.ui.screens.main.profile.liked

import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.domain.use_cases.LikeRecipeUseCase
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class LikedScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    storageRepository: StorageRepository,
    private val likeRecipeUseCase: LikeRecipeUseCase,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {

    val recipes = storageRepository.recipes

    fun isRecipeLiked(recipe: Recipe): Flow<Boolean> = flow {
        emit(likeRecipeUseCase.isRecipeLiked(recipe))
    }
    fun initLikedRecipesID(): Flow<List<String>> = flow {
        val profile = authRepository.getProfile(authRepository.currentUserId) ?: Profile()
        emit(profile.likedRecipes)
    }
    fun onRecipeLikeClick(recipe: Recipe) {
        launchCatching {
            likeRecipeUseCase.onRecipeLikeClick(recipe)
        }
    }
}