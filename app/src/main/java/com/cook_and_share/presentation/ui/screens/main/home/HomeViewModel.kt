package com.cook_and_share.presentation.ui.screens.main.home

import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    logRepository: LogRepository,
    private val storageRepository: StorageRepository
): CookAndShareViewModel(logRepository){

    val recipes = storageRepository.recipes
    val dailyRecipes = storageRepository.dailyRecipes
    fun onRecipeLikeClick(recipe: Recipe) {
        launchCatching {
            storageRepository.update(recipe.copy(likes = recipe.likes + 1))
        }
    }
}