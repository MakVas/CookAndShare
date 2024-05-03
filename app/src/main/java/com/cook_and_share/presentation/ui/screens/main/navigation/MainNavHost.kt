package com.cook_and_share.presentation.ui.screens.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cook_and_share.presentation.util.Main

@Composable
fun MainNavHost(
    rootNavController: NavHostController,
    navController: NavHostController
) {
    val selectedCategories: MutableState<List<String>> = mutableStateOf(emptyList())
    NavHost(
        navController = navController,
        startDestination = Main.HomeScreen.route
    ) {
        mainGraph(rootNavController, navController, selectedCategories)
    }
}