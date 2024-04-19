package com.cook_and_share.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cook_and_share.presentation.ui.screens.main.profile.info.InfoScreen
import com.cook_and_share.presentation.ui.screens.main.profile.liked.LikedScreen
import com.cook_and_share.presentation.ui.screens.main.profile.settings.SettingsScreen
import com.cook_and_share.presentation.ui.screens.splash.splash.SplashScreen
import com.cook_and_share.presentation.util.NavGraphs
import com.cook_and_share.presentation.util.Screens

fun NavGraphBuilder.cookAndShareGraph(appState: CookAndShareState) {
    navigation(
        route = NavGraphs.CookAndShare.route,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
        }

        composable(Screens.Settings.route) {
            SettingsScreen(
                popUp = { appState.popUp() },
                restartApp = { route -> appState.clearAndNavigate(route) }
            )
        }

        composable(Screens.Info.route) {
            InfoScreen(popUp = { appState.popUp() })
        }

        composable(Screens.Liked.route) {
            LikedScreen(popUp = { appState.popUp() })
        }
    }
}