package com.cook_and_share.presentation.ui.screens.auth.sign_up

import androidx.compose.runtime.mutableStateOf
import com.cook_and_share.R
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.ui.components.snackbar.SnackbarManager
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Screens
import com.cook_and_share.presentation.util.isValidEmail
import com.cook_and_share.presentation.util.isValidPassword
import com.cook_and_share.presentation.util.passwordMatches
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    logRepository: LogRepository
): CookAndShareViewModel(logRepository) {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

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
            authRepository.createAccount(email, password)
            openAndPopUp(Screens.Main.route, Screens.SignUpScreen.route)
        }
    }

    fun onLoginClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(Screens.LoginScreen.route, Screens.SignUpScreen.route)
    }

}