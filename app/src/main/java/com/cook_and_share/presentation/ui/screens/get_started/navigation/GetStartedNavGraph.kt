package com.cook_and_share.presentation.ui.screens.get_started.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.cook_and_share.presentation.navigate
import com.cook_and_share.presentation.navigateAndPopUp
import com.cook_and_share.presentation.navigation.customComposable
import com.cook_and_share.presentation.ui.screens.get_started.GetStartedViewModel
import com.cook_and_share.presentation.ui.screens.get_started.allergies.AllergiesFragment
import com.cook_and_share.presentation.ui.screens.get_started.dislikes.DislikesFragment
import com.cook_and_share.presentation.ui.screens.get_started.preferences.PreferencesFragment
import com.cook_and_share.presentation.ui.screens.get_started.sign_up.SignUpScreen
import com.cook_and_share.presentation.util.GetStarted

fun NavGraphBuilder.getStartedGraph(
    viewModel: GetStartedViewModel,
    rootNavController: NavHostController,
    navController: NavHostController
) {
    customComposable(GetStarted.Preferences.route) {
        PreferencesFragment(
            viewModel = viewModel,
            navigate = { route -> navigate(navController, route) },
            // openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }
    customComposable(GetStarted.Allergies.route) {
        AllergiesFragment(
            viewModel = viewModel,
            navigate = { route -> navigate(navController, route) },
        )
    }
    customComposable(GetStarted.Dislikes.route) {
        DislikesFragment(
            viewModel = viewModel,
            navigate = { route -> navigate(navController, route) },
        )
    }
    customComposable(GetStarted.SignUpScreen.route) {
        SignUpScreen(
            openAndPopUp = { route, popUp -> navigateAndPopUp(rootNavController, route, popUp) }
        )
    }
}