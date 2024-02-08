package com.mealmentor.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mealmentor.logic.database.sign_in.UserData
import com.mealmentor.logic.navigation.getBottomNavigationItems
import com.mealmentor.ui.pages.screens.main.ProfileScreen
import com.mealmentor.ui.pages.screens.Screens
import com.mealmentor.ui.pages.screens.main.AddRecipeScreen
import com.mealmentor.ui.pages.screens.main.HomeScreen
import com.mealmentor.ui.pages.screens.main.SearchScreen

@Composable
fun MainPage(
    userData: UserData?,
    onSignOut: () -> Unit
) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(navController)
        }
    ) {values ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.name,
            modifier = Modifier
                .padding(values)
        ) {
            composable(Screens.Home.name) {
                HomeScreen()
            }
            composable(Screens.AddRecipe.name) {
                AddRecipeScreen()
            }
            composable(Screens.Search.name) {
                SearchScreen()
            }
            composable(Screens.Profile.name) {
                ProfileScreen(userData = userData, onSignOut = onSignOut)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        getBottomNavigationItems().forEachIndexed { _, item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    //Here can be a navController
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.inversePrimary,
                    selectedTextColor = MaterialTheme.colorScheme.inversePrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                    indicatorColor = MaterialTheme.colorScheme.secondary
                ),
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (currentDestination?.hierarchy?.any { it.route == item.route } == true)
                                item.selectedIcon
                            else item.unselectedIcon,
                            contentDescription = item.title,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }
    }
}