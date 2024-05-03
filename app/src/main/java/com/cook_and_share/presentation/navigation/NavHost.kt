package com.cook_and_share.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.cook_and_share.presentation.CookAndShareState

@Composable
fun CookAndShareNavHost(
    appState: CookAndShareState,
    isDarkTheme: Boolean,
    toggleTheme: () -> Unit
) {
    val rootNavController = appState.navController
    NavHost(
        navController = rootNavController,
        startDestination = NavGraphs.SplashScreen.route
    ) {
        cookAndShareNavGraph(
            rootNavController = rootNavController,
            isDarkTheme = isDarkTheme,
            toggleTheme = toggleTheme
        )
    }
}