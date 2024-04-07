package com.cook_and_share.presentation.ui.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {

    val showError = mutableStateOf(false)

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false
        if (authRepository.hasUser) openAndPopUp(Screens.Main.route, Screens.SplashScreen.route)
        else openAndPopUp(Screens.LoginScreen.route, Screens.SplashScreen.route)
    }
}