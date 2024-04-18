package com.cook_and_share.presentation

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cook_and_share.presentation.ui.components.snackbar.SnackbarManager
import com.cook_and_share.presentation.ui.screens.auth.ForgotPassScreen
import com.cook_and_share.presentation.ui.screens.auth.login.LoginScreen
import com.cook_and_share.presentation.ui.screens.auth.sign_up.sign_up.SignUpScreen
import com.cook_and_share.presentation.ui.screens.MainPage
import com.cook_and_share.presentation.ui.screens.auth.sign_up.get_started.GetStartedScreen
import com.cook_and_share.presentation.ui.theme.CookAndShareTheme
import com.cook_and_share.presentation.util.Screens
import com.cook_and_share.presentation.ui.screens.main.profile.info.InfoScreen
import com.cook_and_share.presentation.ui.screens.main.profile.liked.LikedScreen
import com.cook_and_share.presentation.ui.screens.main.profile.settings.SettingsScreen
import com.cook_and_share.presentation.ui.screens.splash.entry.EntryScreen
import com.cook_and_share.presentation.ui.screens.splash.splash.SplashScreen
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CookAndShareApp() {
    CookAndShareTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()

            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = appState.snackbarHostState,
                        snackbar = { snackbarData ->
                            Snackbar(
                                shape = RoundedCornerShape(16.dp),
                                snackbarData = snackbarData,
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.onTertiary
                            )
                        }
                    )
                }
            ) {
                NavHost(
                    navController = appState.navController,
                    startDestination = Screens.SplashScreen.route
                ) {
                    cookAndShareGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(snackbarHostState, navController, snackbarManager, resources, coroutineScope) {
        CookAndShareState(
            snackbarHostState,
            navController,
            snackbarManager,
            resources,
            coroutineScope
        )
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

fun NavGraphBuilder.cookAndShareGraph(appState: CookAndShareState) {

    composable(route = Screens.EntryScreen.route) {
        EntryScreen(
            navigate = { route -> appState.navigate(route) },
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }

    composable(route = Screens.GetStarted.route) {
        GetStartedScreen(
            popUp = { appState.popUp() },
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }

    composable(route = Screens.LoginScreen.route) {
        LoginScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }

    composable(route = Screens.SignUpScreen.route) {
        SignUpScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }

    composable(route = Screens.ForgotPassScreen.route) {
        ForgotPassScreen()
    }

    composable(route = Screens.SplashScreen.route) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(Screens.Main.route) {
        MainPage(mainNavController = appState.navController)
    }

    composable(Screens.Settings.route) {
        SettingsScreen(
            popUp = { appState.popUp() },
            restartApp = { route -> appState.clearAndNavigate(route) }
        )
    }

    composable(Screens.Info.route) {
        InfoScreen(popUp = { appState.popUp() })
    }

    composable(Screens.Liked.route) {
        LikedScreen(popUp = { appState.popUp() })
    }
}