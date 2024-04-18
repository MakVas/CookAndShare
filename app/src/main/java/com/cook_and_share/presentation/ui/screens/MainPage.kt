package com.cook_and_share.presentation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.getBottomNavigationItems
import com.cook_and_share.presentation.util.Screens
import com.cook_and_share.presentation.ui.screens.main.add_recipe.add_recipe.AddRecipeScreen
import com.cook_and_share.presentation.ui.screens.main.add_recipe.categories.CategoriesScreen
import com.cook_and_share.presentation.ui.screens.main.add_recipe.ingredients.IngredientsScreen
import com.cook_and_share.presentation.ui.screens.main.home.HomeScreen
import com.cook_and_share.presentation.ui.screens.main.profile.profile.ProfileScreen
import com.cook_and_share.presentation.ui.screens.main.search.SearchScreen

@Composable
fun MainPage(
    mainNavController: NavHostController
) {
    val currentText = remember { mutableIntStateOf(R.string.app_name) }

    val navController = rememberNavController()

    val test = remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            NavigationBar(navController)
        }
    ) { values ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.route,
            modifier = Modifier
                .padding(values)
        ) {
            composable(Screens.HomeScreen.route) {
                currentText.intValue = R.string.app_name
                HomeScreen()
            }
            composable(Screens.AddRecipeScreen.route) {
                currentText.intValue = R.string.preview
                AddRecipeScreen(
                    navController = navController,
                )
            }
            composable(Screens.SearchRecipeScreen.route) {
                currentText.intValue = R.string.search
                SearchScreen()
            }
            composable(Screens.ProfileScreen.route) {
                currentText.intValue = R.string.profile
                ProfileScreen(
                    navController = mainNavController
                )
            }
            composable(Screens.CategoriesScreen.route) {
                currentText.intValue = R.string.categories
                CategoriesScreen(
                    popUp = { navController.popBackStack() },
                    onValueChange = test
                )
            }
            composable(Screens.IngredientsScreen.route) {
                currentText.intValue = R.string.ingredients
                IngredientsScreen(
                    popUp = { navController.popBackStack() },
                )
            }
        }
    }
}

@Composable
private fun NavigationBar(navController: NavController) {
    NavigationBar(
        modifier = Modifier.shadow(elevation = 3.dp),
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        getBottomNavigationItems().forEachIndexed { _, item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any {
                    if (
                        it.route == Screens.AddRecipeScreen.route ||
                        it.route == Screens.IngredientsScreen.route ||
                        it.route == Screens.CategoriesScreen.route
                    ) {
                        Screens.AddRecipeScreen.route == item.route
                    } else it.route == item.route
                } == true,
                onClick = {
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
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                    indicatorColor = MaterialTheme.colorScheme.onSecondary
                ),
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge(
                                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                                    contentColor = MaterialTheme.colorScheme.primary
                                ) {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews) {
                                Badge(containerColor = MaterialTheme.colorScheme.inversePrimary)
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
