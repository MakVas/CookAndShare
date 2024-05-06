package com.cook_and_share.presentation.ui.screens.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cook_and_share.presentation.util.Main
import com.google.mlkit.nl.translate.Translation

@Composable
fun MainNavHost(
    isTranslation: Boolean,
    rootNavController: NavHostController,
    navController: NavHostController
) {
    val selectedCategories: MutableState<List<String>> = remember { mutableStateOf(emptyList()) }
    NavHost(
        navController = navController,
        startDestination = Main.HomeScreen.route
    ) {
        mainGraph(isTranslation, rootNavController, navController, selectedCategories)
    }
}