package com.cook_and_share.presentation.navigation

sealed class NavGraphs(val route: String){
    data object SplashScreen : NavGraphs("splash_screen")
    data object GetStarted : NavGraphs("get_started")
    data object Main : NavGraphs("main")
    data object Login : NavGraphs("login")
}