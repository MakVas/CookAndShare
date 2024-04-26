package com.cook_and_share.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cook_and_share.presentation.clearAndNavigate
import com.cook_and_share.presentation.navigateAndPopUp
import com.cook_and_share.presentation.popUp
import com.cook_and_share.presentation.ui.screens.get_started.GetStartedScreen
import com.cook_and_share.presentation.ui.screens.login.navigation.LoginNavHost
import com.cook_and_share.presentation.ui.screens.main.MainScreen
import com.cook_and_share.presentation.ui.screens.main.profile.info.InfoScreen
import com.cook_and_share.presentation.ui.screens.main.profile.liked.LikedScreen
import com.cook_and_share.presentation.ui.screens.main.profile.settings.SettingsScreen
import com.cook_and_share.presentation.ui.screens.splash.SplashScreen
import com.cook_and_share.presentation.util.ProfileRoutes

fun NavGraphBuilder.cookAndShareNavGraph(
    rootNavController: NavHostController
) {
    customComposable(NavGraphs.Login.route) {
        LoginNavHost(rootNavController)
    }

    customComposable(NavGraphs.Main.route) {
        MainScreen(rootNavController)
    }

    customComposable(NavGraphs.GetStarted.route) {
        val navController = rememberNavController()

        GetStartedScreen(
            rootNavController = rootNavController,
            navController = navController
        )
    }

    customComposable(NavGraphs.SplashScreen.route) {
        SplashScreen(
            openAndPopUp = { route, popUp -> navigateAndPopUp(rootNavController, route, popUp) }
        )
    }

    customComposable(ProfileRoutes.Liked.route) {
        LikedScreen(popUp = { popUp(rootNavController)} )
    }

    customComposable(ProfileRoutes.Info.route) {
        InfoScreen { popUp(rootNavController) }
    }

    customComposable(ProfileRoutes.Settings.route) {
        SettingsScreen (
            popUp = { popUp(rootNavController) },
            restartApp = { route -> clearAndNavigate(rootNavController, route) }
        )
    }
}