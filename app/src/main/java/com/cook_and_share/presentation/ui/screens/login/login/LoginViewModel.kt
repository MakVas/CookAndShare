package com.cook_and_share.presentation.ui.screens.login.login

import androidx.compose.runtime.mutableStateOf
import com.cook_and_share.R
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.navigation.NavGraphs
import com.cook_and_share.presentation.ui.components.snackbar.SnackbarManager
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Login
import com.cook_and_share.presentation.util.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {
    var uiState = mutableStateOf(LoginUiState())
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

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.email_error)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(R.string.password_error)
            return
        }

        launchCatching {
            authRepository.authenticate(email, password)
            openAndPopUp(NavGraphs.Main.route, NavGraphs.Login.route)
        }
    }

    fun onForgotPasswordClick(navigate: (String) -> Unit) {
        navigate(Login.ForgotPassScreen.route)
    }

    fun onSignUpClick(navigate: (String) -> Unit) {
        navigate(Login.EntryScreen.route)
    }
}