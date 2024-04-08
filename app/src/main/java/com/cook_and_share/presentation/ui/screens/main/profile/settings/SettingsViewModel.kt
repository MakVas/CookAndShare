package com.cook_and_share.presentation.ui.screens.main.profile.settings

import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    logRepository: LogRepository
): CookAndShareViewModel(logRepository){
    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            authRepository.signOut()
            restartApp(Screens.SplashScreen.route)
        }
    }
}