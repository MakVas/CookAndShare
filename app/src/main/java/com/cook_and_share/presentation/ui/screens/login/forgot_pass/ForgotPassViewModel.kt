package com.cook_and_share.presentation.ui.screens.login.forgot_pass

import androidx.compose.runtime.mutableStateOf
import com.cook_and_share.R
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.ui.components.snackbar.SnackbarManager
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPassViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {

    var uiState = mutableStateOf(ForgotPassUiState())
        private set
    private val email
        get() = uiState.value.email

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onForgotPasswordClick(popUp: () -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.email_error)
            return
        }

        launchCatching {
            authRepository.sendRecoveryEmail(email)
            SnackbarManager.showMessage(R.string.email_letter_sent)
        }

        popUp()
    }
}