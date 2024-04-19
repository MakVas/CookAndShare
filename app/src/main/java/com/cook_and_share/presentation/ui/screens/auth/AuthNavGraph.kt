package com.cook_and_share.presentation.ui.screens.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cook_and_share.presentation.CookAndShareState
import com.cook_and_share.presentation.ui.screens.auth.login.LoginScreen
import com.cook_and_share.presentation.ui.screens.auth.sign_up.get_started.GetStartedScreen
import com.cook_and_share.presentation.ui.screens.auth.sign_up.sign_up.SignUpScreen
import com.cook_and_share.presentation.ui.screens.splash.entry.EntryScreen
import com.cook_and_share.presentation.util.NavGraphs
import com.cook_and_share.presentation.util.Screens

fun NavGraphBuilder.authGraph(appState: CookAndShareState) {
    navigation(
        route = NavGraphs.Auth.route,
        startDestination = Screens.EntryScreen.route
    ) {

        composable(route = Screens.EntryScreen.route) {
            EntryScreen(
                navigate = { route -> appState.navigate(route) },
                openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
            )
        }

        composable(route = Screens.GetStarted.route) {
            GetStartedScreen(
                popUp = { appState.popUp() },
                openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
            )
        }

        composable(route = Screens.LoginScreen.route) {
            LoginScreen(
                openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
            )
        }

        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(
                openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
            )
        }

        composable(route = Screens.ForgotPassScreen.route) {
            ForgotPassScreen()
        }
    }
}