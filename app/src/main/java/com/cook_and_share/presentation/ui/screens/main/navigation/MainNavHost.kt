package com.cook_and_share.presentation.ui.screens.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cook_and_share.presentation.CookAndShareState
import com.cook_and_share.presentation.navigation.NavGraphs

@Composable
fun MainNavHost(
    appState: CookAndShareState
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavGraphs.Main.route
    ) {
        mainGraph(
            appState = appState
        )
    }
}