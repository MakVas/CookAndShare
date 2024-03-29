package com.cook_and_share.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cook_and_share.R
import com.cook_and_share.core.presentation.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                text = R.string.settings,
                scrollBehavior = scrollBehavior
            )
        }
    ) { values ->
        Box(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            NestedScrolling()
        }
    }
}

@Composable
private fun NestedScrolling() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.padding(top = 16.dp))

        Text(
            text = "IDK JUST TEXT",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}