package com.cook_and_share.presentation.ui.screens.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cook_and_share.presentation.CookAndShareState
import com.cook_and_share.presentation.ui.screens.main.add_recipe.add_recipe.AddRecipeScreen
import com.cook_and_share.presentation.ui.screens.main.add_recipe.categories.CategoriesScreen
import com.cook_and_share.presentation.ui.screens.main.add_recipe.ingredients.IngredientsScreen
import com.cook_and_share.presentation.ui.screens.main.home.HomeScreen
import com.cook_and_share.presentation.ui.screens.main.profile.profile.ProfileScreen
import com.cook_and_share.presentation.ui.screens.main.search.SearchScreen
import com.cook_and_share.presentation.util.Main

fun NavGraphBuilder.mainGraph(
    appState: CookAndShareState
) {
    composable(Main.HomeScreen.route) {
        HomeScreen()
    }
    composable(Main.AddRecipeScreen.route) {
        AddRecipeScreen(
            navigate = { route -> appState.navigate(route) },
            popUp = { appState.popUp() },
        )
    }
    composable(Main.SearchRecipeScreen.route) {
        SearchScreen()
    }
    composable(Main.ProfileScreen.route) {
        ProfileScreen(
            navigate = { route -> appState.navigate(route) },
        )
    }
    composable(Main.CategoriesScreen.route) {
        CategoriesScreen(
            popUp = { appState.popUp() }
        )
    }
    composable(Main.IngredientsScreen.route) {
        IngredientsScreen(
            popUp = { appState.popUp() },
        )
    }
}