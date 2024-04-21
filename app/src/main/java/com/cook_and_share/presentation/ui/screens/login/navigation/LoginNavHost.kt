package com.cook_and_share.presentation.ui.screens.login.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cook_and_share.presentation.CookAndShareState
import com.cook_and_share.presentation.util.Login

@Composable
fun LoginNavHost(
    appState: CookAndShareState
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Login.EntryScreen.route
    ) {
        loginGraph(appState)
    }
}