package com.mealmentor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mealmentor.presentation.SplashScreen
import com.mealmentor.presentation.authentication.AuthViewModel
import com.mealmentor.util.Screens
import com.mealmentor.presentation.main.MainPage
import com.mealmentor.presentation.authentication.LoginScreen
import com.mealmentor.presentation.authentication.SignUpScreen
import com.mealmentor.ui.theme.MealMentorKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealMentorKotlinTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    val viewModel = hiltViewModel<AuthViewModel>()
                    val navController = rememberNavController()
                    Navigation(viewModel ,navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(
    viewModel: AuthViewModel,
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        composable(route = Screens.LoginScreen.route) {
            LoginScreen(viewModel = viewModel ,navController = navController)
        }

        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(viewModel = viewModel ,navController = navController)
        }

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(viewModel = viewModel ,navController = navController)
        }

        composable(Screens.Main.route) {
            MainPage(viewModel = viewModel ,localNavController = navController)
        }
    }
}
