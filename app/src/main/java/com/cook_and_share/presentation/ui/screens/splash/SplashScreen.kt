package com.cook_and_share.presentation.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.theme.CookAndShareTheme
import com.cook_and_share.presentation.ui.theme.Typography
import kotlinx.coroutines.delay

private const val SPLASH_TIMEOUT = 1000L

@Composable
fun SplashScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    SplashScreenContent(
        modifier = Modifier
            .padding(vertical = 50.dp)
            .fillMaxSize()
            .background(colorScheme.background),
        onAppStart = { viewModel.onAppStart(openAndPopUp) },
        isError = viewModel.showError.value
    )
}

@Composable
private fun SplashScreenContent(
    modifier: Modifier = Modifier,
    onAppStart: () -> Unit,
    isError: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isError) {
            Text(text = stringResource(id = R.string.generic_error))
            SecondaryButton(
                modifier = Modifier,
                label = R.string.try_again
            ) { onAppStart() }
        } else {
            Text(
                text = stringResource(id = R.string.app_name_caps),
                textAlign = TextAlign.Center,
                color = colorScheme.onPrimary,
                style = Typography.displayLarge
            )
            Image(
                painter = painterResource(id = R.drawable.splash_screen_img),
                contentDescription = "Cook&Share Logo"
            )
        }
    }

    LaunchedEffect(true) {
        delay(SPLASH_TIMEOUT)
        onAppStart()
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    CookAndShareTheme {
        SplashScreenContent(
            onAppStart = { },
            isError = true
        )
    }
}