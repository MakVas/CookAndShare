package com.cook_and_share

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
import com.cook_and_share.presentation.SplashScreen
import com.cook_and_share.presentation.authentication.AuthViewModel
import com.cook_and_share.util.Screens
import com.cook_and_share.presentation.main.MainPage
import com.cook_and_share.presentation.authentication.LoginScreen
import com.cook_and_share.presentation.authentication.SignUpScreen
import com.cook_and_share.ui.theme.CookAndShare
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookAndShare {
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
