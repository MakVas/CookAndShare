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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cook_and_share.presentation.ui.components.BottomBar
import com.cook_and_share.presentation.ui.components.snackbar.SnackbarManager
import com.cook_and_share.presentation.ui.screens.auth.authGraph
import com.cook_and_share.presentation.ui.screens.main.mainGraph
import com.cook_and_share.presentation.ui.theme.CookAndShareTheme
import com.cook_and_share.presentation.util.NavGraphs
import com.cook_and_share.presentation.util.Screens
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CookAndShareApp() {
    CookAndShareTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()

            val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

            val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            when (currentDestination?.route){

                Screens.HomeScreen.route -> {
                    bottomBarState.value = true
                }

                Screens.AddRecipeScreen.route -> {
                    bottomBarState.value = true
                }

                Screens.SearchRecipeScreen.route -> {
                    bottomBarState.value = true
                }

                Screens.ProfileScreen.route -> {
                    bottomBarState.value = true
                }

                Screens.CategoriesScreen.route -> {
                    bottomBarState.value = true
                }

                Screens.IngredientsScreen.route -> {
                    bottomBarState.value = true
                }

                else -> {
                    bottomBarState.value = false
                }
            }

            Scaffold(
                bottomBar = {
                    if (bottomBarState.value) {
                        BottomBar(navController = appState.navController)
                    }
                },
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
                    startDestination = NavGraphs.CookAndShare.route
                ) {
                    cookAndShareGraph(appState)
                    authGraph(appState)
                    mainGraph(appState)
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