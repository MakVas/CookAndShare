package com.cook_and_share.presentation.ui.screens.main.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cook_and_share.presentation.ui.screens.main.add_recipe.AddRecipeViewModel
import com.cook_and_share.presentation.util.Main

@Composable
fun MainNavHost(
    isTranslation: Boolean,
    rootNavController: NavHostController,
    navController: NavHostController
) {
    val viewModel: AddRecipeViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Main.HomeScreen.route
    ) {
        mainGraph(isTranslation, rootNavController, navController, viewModel)
    }
}