package com.cook_and_share.presentation.ui.screens.main.profile.settings

import androidx.compose.runtime.mutableStateOf
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.navigation.NavGraphs
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {
    val isExitAccDialog = mutableStateOf(false)
    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            authRepository.signOut()
            restartApp(NavGraphs.SplashScreen.route)
        }
    }
}