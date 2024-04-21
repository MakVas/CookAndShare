package com.cook_and_share.presentation.ui.screens.login.entry

import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.navigation.NavGraphs
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    logRepository: LogRepository
): CookAndShareViewModel(logRepository){
    fun onGetStartedClick(navigate: (String) -> Unit) {
        navigate(NavGraphs.GetStarted.route)
    }
    fun onLoginClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(NavGraphs.Login.route, Login.EntryScreen.route)
    }
}