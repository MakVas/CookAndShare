package com.cook_and_share.presentation.ui.screens.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.cook_and_share.presentation.navigate
import com.cook_and_share.presentation.navigateAndPopUp
import com.cook_and_share.presentation.navigation.customComposable
import com.cook_and_share.presentation.ui.screens.login.ForgotPassScreen
import com.cook_and_share.presentation.ui.screens.login.entry.EntryScreen
import com.cook_and_share.presentation.ui.screens.login.login.LoginScreen
import com.cook_and_share.presentation.util.Login

fun NavGraphBuilder.loginGraph(
    rootNavController: NavHostController,
    navController: NavHostController
) {
    customComposable(Login.EntryScreen.route) {
        EntryScreen(
            navigate = { route -> navigate(rootNavController, route) },
            openAndPopUp = { route, popUp -> navigateAndPopUp(navController, route, popUp) }
        )
    }
    customComposable(Login.LoginScreen.route) {
        LoginScreen(
            navigate = { route -> navigate(navController, route) },
            openAndPopUp = { route, popUp -> navigateAndPopUp(rootNavController, route, popUp) }
        )
    }
    customComposable(Login.ForgotPassScreen.route) {
        ForgotPassScreen()
    }
}