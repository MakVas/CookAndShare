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
import com.google.android.gms.auth.api.identity.Identity
import com.mealmentor.logic.database.sign_in.GoogleAuthClient
import com.mealmentor.presentation.SplashScreen
import com.mealmentor.presentation.authentication.AuthenticationViewModel
import com.mealmentor.util.Screens
import com.mealmentor.presentation.main.MainPage
import com.mealmentor.presentation.authentication.LoginScreen
import com.mealmentor.presentation.authentication.SignUpScreen
import com.mealmentor.ui.theme.MealMentorKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthClient by lazy {
        GoogleAuthClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealMentorKotlinTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val authViewModel: AuthenticationViewModel = hiltViewModel()
                    Navigation(navController, authViewModel)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, authViewModel: AuthenticationViewModel) {
    //val viewModel = hiltViewModel<FirebaseViewModel>()

    //NotificationMessage(viewModel = viewModel)

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController = navController, viewModel = authViewModel)
        }

        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController = navController, viewModel = authViewModel)
        }

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController, authViewModel = authViewModel)
        }

        composable(Screens.Main.route) {
            MainPage(navController = navController, viewModel = authViewModel)
        }
    }
}
