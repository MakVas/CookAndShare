package com.mealmentor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.mealmentor.logic.database.sign_in.GoogleAuthClient
import com.mealmentor.logic.database.sign_in.SignInViewModel
import com.mealmentor.ui.pages.AuthPage
import com.mealmentor.ui.pages.MainPage
import com.mealmentor.ui.pages.screens.SplashScreen
import com.mealmentor.ui.theme.MealMentorKotlinTheme
import kotlinx.coroutines.launch

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
            val navController = rememberNavController()
            NavHost(
                navController = navController, startDestination = "splash_screen"
            ) {


                composable("splash_screen") {
                    SplashScreen {
                        if (googleAuthClient.getSignedInUser() != null) {
                            navController.popBackStack()
                            navController.navigate("main")
                        } else {
                            navController.popBackStack()
                            navController.navigate("auth")
                        }
                    }
                }


                // Вікно авторизації
                composable("auth") {
                    val viewModel = viewModel<SignInViewModel>()
                    val state by viewModel.state.collectAsState()

                    val launcher =
                        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) lifecycleScope.launch {
                                    val signInResult = googleAuthClient.signInWithIntent(
                                        result.data ?: return@launch
                                    )
                                    viewModel.onSignInResult(signInResult)
                                }

                            })

                    LaunchedEffect(key1 = state.isSignInSuccessful) {
                        if (state.isSignInSuccessful) {
                            Toast.makeText(
                                applicationContext, "Sign in successful", Toast.LENGTH_LONG
                            ).show()
                            navController.popBackStack()
                            navController.navigate("main")
                            viewModel.resetState()
                        }
                    }

                    AuthPage(state = state, onGoogleSignInClick = {
                        lifecycleScope.launch {
                            val signInIntentSender = googleAuthClient.signIn()
                            launcher.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )
                        }
                    })
                }

                // Вікно профілю
                composable("main") {
                    MainPage(userData = googleAuthClient.getSignedInUser(), onSignOut = {
                        lifecycleScope.launch {
                            googleAuthClient.signOut()
                            Toast.makeText(
                                applicationContext, "Sign out successful", Toast.LENGTH_LONG
                            ).show()
                            navController.popBackStack()
                            navController.navigate("auth")
                        }
                    })
                }
            }
        }
    }
}
