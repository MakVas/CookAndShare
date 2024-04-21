package com.cook_and_share.presentation.ui.screens.get_started.get_started

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cook_and_share.presentation.CookAndShareState
import com.cook_and_share.presentation.ui.components.ProgressBar
import com.cook_and_share.presentation.ui.components.TopAppBarBackIcon
import com.cook_and_share.presentation.ui.components.TopBar
import com.cook_and_share.presentation.ui.screens.get_started.navigation.GetStartedNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetStartedScreen(
    popUp: () -> Unit,
    appState: CookAndShareState
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    GetStartedScreenContent(
        popUp = popUp,
        scrollBehavior = scrollBehavior,
        appState = appState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GetStartedScreenContent(
    popUp: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    appState: CookAndShareState
) {
    Scaffold(
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                title = {
                    ProgressBar(25f)
                },
                iconButton = {
                    TopAppBarBackIcon(popUp)
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            GetStartedNavHost(appState)
        }
    }
}