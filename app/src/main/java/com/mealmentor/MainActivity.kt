package com.mealmentor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.mealmentor.logic.database.sign_in.FirebaseViewModel
import com.mealmentor.logic.database.sign_in.GoogleAuthClient
import com.mealmentor.logic.database.sign_in.SignInViewModel
import com.mealmentor.logic.navigation.NavigationRoutes
import com.mealmentor.ui.additional.NotificationMessage
import com.mealmentor.ui.pages.AuthPage
import com.mealmentor.ui.pages.MainPage
import com.mealmentor.ui.pages.screens.SplashScreen
import com.mealmentor.ui.pages.screens.elements.RecipeViewModel
import com.mealmentor.ui.theme.MealMentorKotlinTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
                Navigation()
            }
        }
    }

    @Composable
    fun Navigation() {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            val viewModel = hiltViewModel<FirebaseViewModel>()
            val navController = rememberNavController()

            NotificationMessage(viewModel = viewModel)

            NavHost(
                navController = navController, startDestination = NavigationRoutes.Splash.name
            ) {


                composable(NavigationRoutes.Splash.name) {
                    SplashScreen {
                        if (googleAuthClient.getSignedInUser() != null) {
                            navController.popBackStack()
                            navController.navigate(NavigationRoutes.Main.name)
                        } else {
                            navController.popBackStack()
                            navController.navigate(NavigationRoutes.Auth.name)
                        }
                    }
                }

                // Вікно авторизації
                composable(NavigationRoutes.Auth.name) {
                    val googleViewModel = viewModel<SignInViewModel>()
                    val state by googleViewModel.state.collectAsState()

                    val launcher =
                        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) lifecycleScope.launch {
                                    val signInResult = googleAuthClient.signInWithIntent(
                                        result.data ?: return@launch
                                    )
                                    googleViewModel.onSignInResult(signInResult)
                                }

                            })

                    LaunchedEffect(key1 = state.isSignInSuccessful) {
                        if (state.isSignInSuccessful) {
                            Toast.makeText(
                                applicationContext, "Sign in successful", Toast.LENGTH_LONG
                            ).show()
                            navController.popBackStack()
                            navController.navigate(NavigationRoutes.Main.name)
                            googleViewModel.resetState()
                        }
                    }

                    AuthPage(
                        state = state,
                        viewModel = viewModel,
                        onGoogleSignInClick = {
                            lifecycleScope.launch {
                                val signInIntentSender = googleAuthClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )
                            }
                        },
                        onMainNavigation = {
                            navController.popBackStack()
                            navController.navigate(NavigationRoutes.Main.name)
                        }
                    )
                }

                // Вікно профілю
                composable(NavigationRoutes.Main.name) {
                    val recipeViewModel: RecipeViewModel by viewModels()
                    MainPage(
                        viewModel = recipeViewModel,
                        userData = googleAuthClient.getSignedInUser(),
                        onSignOut = {
                        lifecycleScope.launch {
                            googleAuthClient.signOut()
                            Toast.makeText(
                                applicationContext, "Sign out successful", Toast.LENGTH_LONG
                            ).show()
                            navController.popBackStack()
                            navController.navigate(NavigationRoutes.Auth.name)
                        }
                    })
                }
            }
        }
    }
}

