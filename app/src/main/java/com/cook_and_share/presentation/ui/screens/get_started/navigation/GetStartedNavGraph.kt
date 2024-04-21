package com.cook_and_share.presentation.ui.screens.get_started.navigation

import androidx.navigation.NavGraphBuilder
import com.cook_and_share.presentation.CookAndShareState
import com.cook_and_share.presentation.navigation.customComposable
import com.cook_and_share.presentation.ui.screens.get_started.fragments.AllergiesFragment
import com.cook_and_share.presentation.ui.screens.get_started.fragments.DislikesFragment
import com.cook_and_share.presentation.ui.screens.get_started.fragments.PreferencesFragment
import com.cook_and_share.presentation.ui.screens.get_started.sign_up.SignUpScreen
import com.cook_and_share.presentation.util.GetStarted

fun NavGraphBuilder.getStartedGraph(appState: CookAndShareState) {
    customComposable(GetStarted.Preferences.route) {
        PreferencesFragment(
            // navigate = { route -> appState.navigate(route) },
            // openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }
    customComposable(GetStarted.Allergies.route) {
        AllergiesFragment(
            //openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }
    customComposable(GetStarted.Dislikes.route) {
        DislikesFragment()
    }
    customComposable(GetStarted.SignUpScreen.route){
        SignUpScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }
}