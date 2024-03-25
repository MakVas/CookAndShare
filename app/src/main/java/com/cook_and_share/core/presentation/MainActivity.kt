package com.cook_and_share.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cook_and_share.R
import com.cook_and_share.splash.presentation.SplashScreen
import com.cook_and_share.auth.presentation.AuthViewModel
import com.cook_and_share.core.presentation.util.Screens
import com.cook_and_share.core.presentation.ui.screens.MainPage
import com.cook_and_share.auth.presentation.LoginScreen
import com.cook_and_share.auth.presentation.SignUpScreen
import com.cook_and_share.core.presentation.ui.components.getDrawerItems
import com.cook_and_share.info.presentation.InfoScreen
import com.cook_and_share.settings.presentation.SettingsScreen
import com.cook_and_share.core.presentation.ui.theme.CookAndShare
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

    val currentText = remember { mutableIntStateOf(R.string.app_name) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    DrawerBar(currentText, viewModel, navController, scope, drawerState) {
        NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

            composable(route = Screens.LoginScreen.route) {
                LoginScreen(viewModel = viewModel, navController = navController)
            }

            composable(route = Screens.SignUpScreen.route) {
                SignUpScreen(viewModel = viewModel, navController = navController)
            }

            composable(route = Screens.SplashScreen.route) {
                SplashScreen(viewModel = viewModel, navController = navController)
            }

            composable(Screens.Main.route) {
                MainPage(currentText = currentText, scope = scope, drawerState = drawerState)
            }
            composable(Screens.Settings.route) {
                SettingsScreen(scope = scope, drawerState = drawerState)
            }
            composable(Screens.Info.route) {
                InfoScreen(scope = scope, drawerState = drawerState)
            }
        }
    }
}

@Composable
private fun DrawerBar(
    currentText: MutableState<Int>,
    viewModel: AuthViewModel?,
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val isSearchScreenActive = currentText.value == R.string.search ||
            currentText.value == R.string.ingredients ||
            currentText.value == R.string.categories

    val gestureEnabled = !isSearchScreenActive

    ModalNavigationDrawer(
        gesturesEnabled = gestureEnabled,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape,
                drawerContainerColor = MaterialTheme.colorScheme.primary
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                getDrawerItems().forEachIndexed { index, drawerItem ->

                    Spacer(modifier = Modifier.height(4.dp))

                    NavigationDrawerItem(
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            unselectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.inversePrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            selectedIconColor = MaterialTheme.colorScheme.inversePrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedBadgeColor = MaterialTheme.colorScheme.inversePrimary,
                            unselectedBadgeColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        label = { Text(drawerItem.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            // Use NavController to navigate to the corresponding screen
                            navController.navigate(drawerItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    drawerItem.selectedIcon
                                } else drawerItem.unselectedIcon,
                                contentDescription = drawerItem.title
                            )
                        },
                        badge = {
                            drawerItem.badgeCount?.let {
                                Text(text = drawerItem.badgeCount.toString())
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = MaterialTheme.colorScheme.tertiary,
                        unselectedContainerColor = MaterialTheme.colorScheme.tertiary,
                        selectedTextColor = MaterialTheme.colorScheme.onTertiary,
                        unselectedTextColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.sign_out),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    selected = false,
                    onClick = {
                        viewModel?.logout()
                        navController.navigate(Screens.LoginScreen.route) {
                            popUpTo(Screens.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        },
        drawerState = drawerState,
    ) {
        content()
    }
}
