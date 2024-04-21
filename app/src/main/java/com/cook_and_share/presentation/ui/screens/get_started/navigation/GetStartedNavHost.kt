package com.cook_and_share.presentation.ui.screens.get_started.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cook_and_share.presentation.CookAndShareState
import com.cook_and_share.presentation.util.GetStarted

@Composable
fun GetStartedNavHost(
    appState: CookAndShareState
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = GetStarted.Preferences.route
    ) {
        getStartedGraph(appState)
    }
}