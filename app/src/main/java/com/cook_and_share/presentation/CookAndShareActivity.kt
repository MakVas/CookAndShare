package com.cook_and_share.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.cook_and_share.presentation.ui.components.network.ConnectivityObserver
import com.cook_and_share.presentation.ui.components.network.NetworkConnectionDialog
import com.cook_and_share.presentation.ui.components.network.NetworkConnectivityObserver
import com.cook_and_share.presentation.ui.theme.CookAndShareTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        connectivityObserver.observe().onEach {
            println("Status is $it")
        }.launchIn(lifecycleScope)

        setContent {
            val status by connectivityObserver.observe().collectAsState(
                initial = ConnectivityObserver.Status.Unavailable
            )
            var darkTheme by remember { mutableStateOf(false) }
            var isTranslation by remember { mutableStateOf(false) }
            if (status == ConnectivityObserver.Status.Available) {
                CookAndShareApp(
                    isTranslation = isTranslation,
                    toggleTranslation = { isTranslation = !isTranslation },
                    darkTheme = darkTheme,
                    toggleTheme = { darkTheme = !darkTheme }
                )
            } else {
                CookAndShareTheme {
                    NetworkConnectionDialog()
                }
            }
        }
    }
}