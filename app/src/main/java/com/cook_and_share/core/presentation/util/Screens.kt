package com.cook_and_share.core.presentation.util

sealed class Screens(val route: String) {

    //Activity navigation
    data object SplashScreen : Screens("splash_screen")
    data object LoginScreen : Screens("login_screen")
    data object SignUpScreen : Screens("sign_up_screen")
    data object ForgotPassScreen: Screens("forgot_pass_screen")
    data object Main : Screens("main")

    //Main navigation
    data object HomeScreen : Screens("home_screen")
    data object AddRecipeScreen : Screens("add_recipe_screen")
    data object SearchRecipeScreen : Screens("search_recipe_screen")
    data object ProfileScreen : Screens("profile_screen")
    data object CategoriesScreen : Screens("categories_screen")
    data object IngredientsScreen : Screens("ingredients_screen")

    //Profile bottom sheet navigation
    data object Settings : Screens("settings")
    data object Info : Screens("info")
    data object Liked : Screens("liked")

}