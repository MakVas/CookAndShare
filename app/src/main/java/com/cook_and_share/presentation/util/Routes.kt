package com.cook_and_share.presentation.util

sealed class GetStarted(val route: String) {
    data object Allergies : GetStarted("allergies")
    data object Dislikes : GetStarted("dislikes")
    data object Preferences : GetStarted("preferences")
    data object SignUpScreen : GetStarted("sign_up_screen")
}
sealed class Login(val route: String) {
    data object LoginScreen : Login("login_screen")
    data object EntryScreen : Login("entry_screen")
    data object ForgotPassScreen : Login("forgot_pass_screen")
}
sealed class Main(val route: String) {
    data object HomeScreen : Main("home_screen")
    data object AddRecipeScreen : Main("add_recipe_screen")
    data object SearchRecipeScreen : Main("search_recipe_screen")
    data object ProfileScreen : Main("profile_screen")
    data object CategoriesScreen : Main("categories_screen")
    data object IngredientsScreen : Main("ingredients_screen")
}
sealed class ProfileRoutes(val route: String) {
    data object AboutMe :  ProfileRoutes("about_me")
    data object Settings : ProfileRoutes("settings")
    data object Info : ProfileRoutes("info")
    data object Liked : ProfileRoutes("liked")

}