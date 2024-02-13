package com.mealmentor.util

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object LoginScreen : Screens("login_screen")
    object SignUpScreen : Screens("sign_up_screen")
    object Main : Screens("main")
    object HomeScreen : Screens("home_screen")
    object AddRecipeScreen : Screens("add_recipe_screen")
    object SearchRecipeScreen : Screens("search_recipe_screen")
    object ProfileScreen : Screens("profile_screen")


    object Settings : Screens("settings")
    object Info : Screens("info")

}