package com.cook_and_share.presentation.ui.screens.auth.sign_up

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.cook_and_share.R
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.ui.components.snackbar.SnackbarManager
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Constants
import com.cook_and_share.presentation.util.Screens
import com.cook_and_share.presentation.util.idFromParameter
import com.cook_and_share.presentation.util.isValidEmail
import com.cook_and_share.presentation.util.isValidPassword
import com.cook_and_share.presentation.util.isValidUsername
import com.cook_and_share.presentation.util.passwordMatches
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    val profile = mutableStateOf(Profile())

    init {
        val profileID = savedStateHandle.get<String>(Constants.PROFILE_ID)
        if (profileID != null) {
            launchCatching {
                profile.value = authRepository.getProfile(profileID.idFromParameter()) ?: Profile()
            }
        }
    }

    private val username
        get() = uiState.value.username
    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onUsernameChange(newValue: String) {
        uiState.value = uiState.value.copy(username = newValue)
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!username.isValidUsername()) {
            SnackbarManager.showMessage(R.string.username_error)
            return
        }

        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(R.string.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(R.string.password_match_error)
            return
        }

        launchCatching {
            Log.wtf("LOG123", "111111111111111111111111111111111111111111")
            authRepository.createAccount(email, password)
            Log.wtf("LOG123", "2222222222222222222222222222222222222222222")
//            profile.value = profile.value.copy(
//                username = uiState.value.username,
//                email = uiState.value.email
//            )
//            val updatedProfile = profile.value
//            authRepository.saveProfile(updatedProfile)
            openAndPopUp(Screens.Main.route, Screens.SignUpScreen.route)
        }
    }

    fun onLoginClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(Screens.LoginScreen.route, Screens.SignUpScreen.route)
    }

}