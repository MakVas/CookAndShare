package com.cook_and_share.presentation.ui.screens.main.home

import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.domain.use_cases.LikeRecipeUseCase
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    storageRepository: StorageRepository,
    private val likeRecipeUseCase: LikeRecipeUseCase,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {

    val recipes = storageRepository.recipes
    val dailyRecipes = storageRepository.dailyRecipes
    fun onRecipeLikeClick(recipe: Recipe) {
        launchCatching {
            likeRecipeUseCase.onRecipeLikeClick(recipe)
        }
    }
}
