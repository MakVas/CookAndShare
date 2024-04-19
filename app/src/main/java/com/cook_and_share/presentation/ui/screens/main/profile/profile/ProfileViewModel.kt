package com.cook_and_share.presentation.ui.screens.main.profile.profile

import androidx.compose.runtime.mutableStateOf
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val storageRepository: StorageRepository,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {

    val profile = mutableStateOf(Profile())
    val recipes = storageRepository.myRecipes

    init {
        launchCatching {
            profile.value = authRepository.getProfile(authRepository.currentUserId) ?: Profile()
        }
    }

    fun onRecipeLikeClick(recipe: Recipe) {
        launchCatching {
            storageRepository.update(recipe.copy(likes = recipe.likes + 1))
        }
    }

    fun onSettingsClick(navigate: (String) -> Unit) {
        navigate(Screens.Settings.route)
    }

    fun onLikedClick(navigate: (String) -> Unit) {
        navigate(Screens.Liked.route)
    }

    fun onInfoClick(navigate: (String) -> Unit) {
        navigate(Screens.Info.route)
    }
}