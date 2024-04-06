package com.cook_and_share.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cook_and_share.splash.presentation.SplashScreen
import com.cook_and_share.auth.presentation.AuthViewModel
import com.cook_and_share.auth.presentation.ForgotPassScreen
import com.cook_and_share.core.presentation.util.Screens
import com.cook_and_share.core.presentation.ui.screens.MainPage
import com.cook_and_share.auth.presentation.LoginScreen
import com.cook_and_share.auth.presentation.SignUpScreen
import com.cook_and_share.profile.presentation.info.InfoScreen
import com.cook_and_share.profile.presentation.settings.SettingsScreen
import com.cook_and_share.core.presentation.ui.theme.CookAndShare
import com.cook_and_share.profile.presentation.liked.LikedScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookAndShare {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<AuthViewModel>()

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        composable(route = Screens.LoginScreen.route) {
            LoginScreen(viewModel = viewModel, navController = navController)
        }

        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(viewModel = viewModel, navController = navController)
        }

        composable(route = Screens.ForgotPassScreen.route) {
            ForgotPassScreen()
        }

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(viewModel = viewModel, navController = navController)
        }

        composable(Screens.Main.route) {
            MainPage(mainNavController = navController)
        }

        composable(Screens.Settings.route) {
            SettingsScreen(navController = navController)
        }

        composable(Screens.Info.route) {
            InfoScreen(navController = navController)
        }

        composable(Screens.Liked.route) {
            LikedScreen(navController = navController)
        }
    }
}