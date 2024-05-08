package com.cook_and_share.presentation.ui.screens.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.cook_and_share.presentation.navigate
import com.cook_and_share.presentation.popUp
import com.cook_and_share.presentation.ui.screens.main.add_recipe.add_recipe.AddRecipeScreen
import com.cook_and_share.presentation.ui.screens.main.add_recipe.AddRecipeViewModel
import com.cook_and_share.presentation.ui.screens.main.add_recipe.categories.CategoriesScreen
import com.cook_and_share.presentation.ui.screens.main.add_recipe.ingredients.IngredientsScreen
import com.cook_and_share.presentation.ui.screens.main.home.HomeScreen
import com.cook_and_share.presentation.ui.screens.main.profile.profile.ProfileScreen
import com.cook_and_share.presentation.ui.screens.main.search.SearchScreen
import com.cook_and_share.presentation.util.Main

fun NavGraphBuilder.mainGraph(
    isTranslation: Boolean,
    rootNavController: NavHostController,
    navController: NavHostController,
    viewModel: AddRecipeViewModel,
) {
    composable(Main.HomeScreen.route) {
        HomeScreen(isTranslation)
    }
    composable(Main.AddRecipeScreen.route) {
        AddRecipeScreen(
            viewModel = viewModel,
            navigate = { route -> navigate(navController, route) },
            popUp = { popUp(navController) },
        )
    }
    composable(Main.SearchRecipeScreen.route) {
        SearchScreen(
            isTranslation = isTranslation
        )
    }
    composable(Main.ProfileScreen.route) {
        ProfileScreen(
            isTranslation = isTranslation,
            navigate = { route -> navigate(rootNavController, route) },
        )
    }
    composable(Main.CategoriesScreen.route) {
        CategoriesScreen(
            viewModel = viewModel,
            popUp = { popUp(navController) }
        )
    }
    composable(Main.IngredientsScreen.route) {
        IngredientsScreen(
            viewModel = viewModel,
            popUp = { popUp(navController) },
        )
    }
}