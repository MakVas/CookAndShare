package com.cook_and_share.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.cook_and_share.presentation.CookAndShareState
import com.cook_and_share.presentation.ui.screens.get_started.get_started.GetStartedScreen
import com.cook_and_share.presentation.ui.screens.login.navigation.LoginNavHost
import com.cook_and_share.presentation.ui.screens.main.navigation.MainNavHost
import com.cook_and_share.presentation.ui.screens.splash.SplashScreen

@Composable
fun CookAndShareNavHost(
    appState: CookAndShareState
) {
    NavHost(
        navController = appState.navController,
        startDestination = NavGraphs.SplashScreen.route
    ) {
        customComposable(NavGraphs.Login.route) {
            LoginNavHost(appState)
        }

        customComposable(NavGraphs.Main.route) {
            MainNavHost(appState)
        }

       customComposable(NavGraphs.GetStarted.route){
           GetStartedScreen(
               popUp = {appState.popUp()},
               appState = appState
           )
       }

        customComposable(NavGraphs.SplashScreen.route) {
            SplashScreen(
                openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
            )
        }
    }
}