package com.cook_and_share.presentation.ui.screens.splash.entry

import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    logRepository: LogRepository
): CookAndShareViewModel(logRepository){
    fun onGetStartedClick(navigate: (String) -> Unit) {
        navigate(Screens.GetStarted.route)
    }
    fun onLoginClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(Screens.LoginScreen.route, Screens.EntryScreen.route)
    }
}