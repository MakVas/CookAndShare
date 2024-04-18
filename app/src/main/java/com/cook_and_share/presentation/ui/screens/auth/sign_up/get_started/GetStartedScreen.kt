package com.cook_and_share.presentation.ui.screens.auth.sign_up.get_started

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cook_and_share.presentation.ui.components.TopAppBarBackIcon
import com.cook_and_share.presentation.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetStartedScreen(
    popUp: () -> Unit,
    openAndPopUp: (String, String) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    GetStartedScreenContent(
        popUp = popUp,
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GetStartedScreenContent(
    popUp: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                title = {
                  ProgressBar()
                },
                scrollBehavior = scrollBehavior,
                iconButton = {
                    TopAppBarBackIcon(popUp)
                }
            )
        }
    ) { values ->
        Box(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(colorScheme.background)
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
            text = "Another text",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun ProgressBar(){
    Box {
        HorizontalDivider(
            modifier = Modifier
                .padding(start = 0.dp, end = 48.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            thickness = 8.dp,
            color = colorScheme.secondary
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(start = 0.dp, end = 48.dp)
                .fillMaxWidth(0.1f)
                .clip(RoundedCornerShape(8.dp)),
            thickness = 8.dp,
            color = colorScheme.onSecondary
        )
    }
}