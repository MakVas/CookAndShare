package com.cook_and_share.presentation.ui.screens.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cook_and_share.presentation.CookAndShareState
import com.cook_and_share.presentation.ui.screens.main.add_recipe.add_recipe.AddRecipeScreen
import com.cook_and_share.presentation.ui.screens.main.add_recipe.categories.CategoriesScreen
import com.cook_and_share.presentation.ui.screens.main.add_recipe.ingredients.IngredientsScreen
import com.cook_and_share.presentation.ui.screens.main.home.HomeScreen
import com.cook_and_share.presentation.ui.screens.main.profile.profile.ProfileScreen
import com.cook_and_share.presentation.ui.screens.main.search.SearchScreen
import com.cook_and_share.presentation.util.NavGraphs
import com.cook_and_share.presentation.util.Screens

fun NavGraphBuilder.mainGraph(appState: CookAndShareState) {
    navigation(
        route = NavGraphs.Main.route,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen()
        }
        composable(Screens.AddRecipeScreen.route) {
            AddRecipeScreen(
                navigate = { route -> appState.navigate(route) },
                popUp = { appState.popUp() },
            )
        }
        composable(Screens.SearchRecipeScreen.route) {
            SearchScreen()
        }
        composable(Screens.ProfileScreen.route) {
            ProfileScreen(
                navigate = { route -> appState.navigate(route) },
            )
        }
        composable(Screens.CategoriesScreen.route) {
            CategoriesScreen(
                popUp = { appState.popUp() }
            )
        }
        composable(Screens.IngredientsScreen.route) {
            IngredientsScreen(
                popUp = { appState.popUp() },
            )
        }
    }
}