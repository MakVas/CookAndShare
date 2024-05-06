package com.cook_and_share.presentation.ui.screens.main.profile.about_me

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import com.cook_and_share.R
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.ui.components.snackbar.SnackbarManager
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutMeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {

    val profile = mutableStateOf(Profile())
    val profileImage = mutableStateOf<Uri?>(null)

    init {
        launchCatching {
            profile.value = authRepository.getProfile(authRepository.currentUserId) ?: Profile()
        }
    }

    fun onUsernameChange(newValue: String) {
        profile.value = profile.value.copy(username = newValue)
    }

    fun onBioChange(newValue: String) {
        profile.value = profile.value.copy(bio = newValue)
    }

    fun onEditClick(popUpScreen: () -> Unit) {
        if (profile.value.username.isEmpty()) {
            SnackbarManager.showMessage(R.string.username_error)
            return
        }

        launchCatching {
            val editedTask = profile.value

            val newProfileImage = if (profileImage.value != null) {
                authRepository.uploadProfileImage(profileImage.value)
            } else {
                editedTask.profileImage
            }
            authRepository.updateProfile(
                editedTask.copy(
                    profileImage = newProfileImage,
                    username = profile.value.username,
                    bio = profile.value.bio
                )
            )
            popUpScreen()
        }
    }

    fun getProfileImage(uri: Uri?) {
        profileImage.value = uri
    }
}