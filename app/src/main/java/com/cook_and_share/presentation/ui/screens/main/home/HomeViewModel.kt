package com.cook_and_share.presentation.ui.screens.main.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.domain.repository.TranslateRepository
import com.cook_and_share.domain.use_cases.LikeRecipeUseCase
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    storageRepository: StorageRepository,
    private val likeRecipeUseCase: LikeRecipeUseCase,
    private val translateRepository: TranslateRepository,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {

    val recipes = storageRepository.recipes
    val dailyRecipes = storageRepository.dailyRecipes

    fun identifyLanguage(text: String): String {
        val second = mutableStateOf("")
        launchCatching {
            second.value = translateRepository.detectLanguage(text)
        }
        return second.value
    }

    fun translateText(
        text: String,
        sourceLanguage: String,
        targetLanguage: String,
        callback: (String) -> Unit
    ) {
        launchCatching {
            translateRepository.translateText(
                text,
                sourceLanguage,
                targetLanguage
            ) { translatedText ->
                callback(translatedText)
            }
        }
    }

    fun isRecipeLiked(recipe: Recipe): Boolean {
        return likeRecipeUseCase.isRecipeLiked(recipe)
    }

    fun onRecipeLikeClick(recipe: Recipe) {
        launchCatching {
            likeRecipeUseCase.onRecipeLikeClick(recipe)
        }
    }
}
