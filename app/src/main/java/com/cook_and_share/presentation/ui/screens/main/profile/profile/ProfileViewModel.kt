package com.cook_and_share.presentation.ui.screens.main.profile.profile

import androidx.compose.runtime.mutableStateOf
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.domain.repository.TranslateRepository
import com.cook_and_share.domain.use_cases.LikeRecipeUseCase
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.ProfileRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val likeRecipeUseCase: LikeRecipeUseCase,
    private val translateRepository: TranslateRepository,
    storageRepository: StorageRepository,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {

    val profile = mutableStateOf(Profile())
    val recipes = storageRepository.myRecipes

    init {
        launchCatching {
            authRepository.currentProfile.collect { profileData ->
                profile.value = profileData
            }
        }
    }

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

    fun onSettingsClick(navigate: (String) -> Unit) {
        navigate(ProfileRoutes.Settings.route)
    }

    fun onLikedClick(navigate: (String) -> Unit) {
        navigate(ProfileRoutes.Liked.route)
    }

    fun onInfoClick(navigate: (String) -> Unit) {
        navigate(ProfileRoutes.Info.route)
    }

    fun onProfileClick(navigate: (String) -> Unit) {
        navigate(ProfileRoutes.AboutMe.route)
    }
}