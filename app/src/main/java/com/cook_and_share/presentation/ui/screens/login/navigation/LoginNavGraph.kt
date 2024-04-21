package com.cook_and_share.presentation.ui.screens.login.navigation

import androidx.navigation.NavGraphBuilder
import com.cook_and_share.presentation.CookAndShareState
import com.cook_and_share.presentation.navigation.customComposable
import com.cook_and_share.presentation.ui.screens.login.ForgotPassScreen
import com.cook_and_share.presentation.ui.screens.login.entry.EntryScreen
import com.cook_and_share.presentation.ui.screens.login.login.LoginScreen
import com.cook_and_share.presentation.util.Login

fun NavGraphBuilder.loginGraph(appState: CookAndShareState) {
    customComposable(Login.EntryScreen.route) {
        EntryScreen(
            navigate = { route -> appState.navigate(route) },
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }
    customComposable(Login.LoginScreen.route) {
        LoginScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }
    customComposable(Login.ForgotPassScreen.route) {
        ForgotPassScreen()
    }
}