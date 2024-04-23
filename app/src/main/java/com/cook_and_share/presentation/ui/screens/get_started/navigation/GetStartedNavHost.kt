package com.cook_and_share.presentation.ui.screens.get_started.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cook_and_share.presentation.ui.screens.get_started.GetStartedViewModel
import com.cook_and_share.presentation.util.GetStarted

@Composable
fun GetStartedNavHost(
    navController: NavHostController,
    rootNavController: NavHostController,
    viewModel: GetStartedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = GetStarted.Preferences.route
    ) {
        getStartedGraph(viewModel, rootNavController, navController)
    }
}