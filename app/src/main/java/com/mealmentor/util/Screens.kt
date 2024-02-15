package com.mealmentor.util

sealed class Screens(val route: String) {
    data object SplashScreen : Screens("splash_screen")
    data object LoginScreen : Screens("login_screen")
    data object SignUpScreen : Screens("sign_up_screen")
    data object Main : Screens("main")
    data object HomeScreen : Screens("home_screen")
    data object AddRecipeScreen : Screens("add_recipe_screen")
    data object SearchRecipeScreen : Screens("search_recipe_screen")
    data object ProfileScreen : Screens("profile_screen")


    data object Settings : Screens("settings")
    data object Info : Screens("info")

}