package com.cook_and_share.presentation.ui.screens.get_started

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cook_and_share.presentation.ui.components.ProgressBar
import com.cook_and_share.presentation.ui.components.TopBar
import com.cook_and_share.presentation.ui.screens.get_started.navigation.GetStartedNavHost
import com.cook_and_share.presentation.util.GetStarted

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetStartedScreen(
    rootNavController: NavHostController,
    navController: NavHostController,
    viewModel: GetStartedViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    GetStartedScreenContent(
        scrollBehavior = scrollBehavior,
        rootNavController = rootNavController,
        navController = navController,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GetStartedScreenContent(
    scrollBehavior: TopAppBarScrollBehavior,
    rootNavController: NavHostController,
    navController: NavHostController,
    viewModel: GetStartedViewModel
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val progress by animateFloatAsState(
        targetValue =
        when (currentDestination?.route) {
            GetStarted.Preferences.route -> 25f
            GetStarted.Allergies.route -> 50f
            GetStarted.Dislikes.route -> 75f
            GetStarted.SignUpScreen.route -> 90f
            else -> 1f
        },
        animationSpec = tween(200),
        label = ""
    )


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                title = {
                    ProgressBar(progress)
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.background)
        ) {
            GetStartedNavHost(
                navController = navController,
                rootNavController = rootNavController,
                viewModel = viewModel
            )
        }
    }
}