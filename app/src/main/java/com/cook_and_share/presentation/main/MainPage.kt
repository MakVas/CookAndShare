package com.cook_and_share.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cook_and_share.R
import com.cook_and_share.presentation.custom.BottomSheet
import com.cook_and_share.presentation.custom.getBottomNavigationItems
import com.cook_and_share.util.Screens
import com.cook_and_share.presentation.main.screens.add_recipe.AddRecipeScreen
import com.cook_and_share.presentation.main.screens.add_recipe.AddRecipeViewModel
import com.cook_and_share.presentation.main.screens.add_recipe.categories.CategoriesScreen
import com.cook_and_share.presentation.main.screens.add_recipe.ingredients.IngredientsScreen
import com.cook_and_share.presentation.main.screens.home.HomeScreen
import com.cook_and_share.presentation.main.screens.profile.ProfileScreen
import com.cook_and_share.presentation.main.screens.search.SearchScreen
import com.cook_and_share.presentation.main.screens.profile.ProfileViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    currentText: MutableState<Int>,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val profileViewModel = hiltViewModel<ProfileViewModel>()
    val addRecipeViewModel = hiltViewModel<AddRecipeViewModel>()

    val navController = rememberNavController()

    val sheetState = rememberModalBottomSheetState()
    val isSheetExpanded = rememberSaveable { mutableStateOf(false) }

    val test = remember { mutableStateOf("") }

        BottomSheet(
            sheetState = sheetState,
            isSheetExpanded = isSheetExpanded
        )
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
                    currentText.value = R.string.app_name
                    HomeScreen(
                        isSheetExpanded = isSheetExpanded,
                        scope = scope,
                        drawerState = drawerState
                    )
                }
                composable(Screens.AddRecipeScreen.route) {
                    currentText.value = R.string.preview
                    AddRecipeScreen(
                        viewModel = addRecipeViewModel,
                        navController = navController,
                        scope = scope,
                        drawerState = drawerState
                    )
                }
                composable(Screens.SearchRecipeScreen.route) {
                    currentText.value = R.string.search
                    SearchScreen(
                        isSheetExpanded = isSheetExpanded
                    )
                }
                composable(Screens.ProfileScreen.route) {
                    currentText.value = R.string.profile
                    ProfileScreen(
                        viewModel = profileViewModel,
                        scope = scope,
                        drawerState = drawerState
                    )
                }
                composable(Screens.CategoriesScreen.route){
                    currentText.value = R.string.categories
                    CategoriesScreen(
                        navController = navController,
                        onValueChange = test
                    )
                }
                composable(Screens.IngredientsScreen.route){
                    currentText.value = R.string.ingredients
                    IngredientsScreen(
                        navController = navController
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
                                Badge (
                                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                                    contentColor = MaterialTheme.colorScheme.primary
                                ){
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
