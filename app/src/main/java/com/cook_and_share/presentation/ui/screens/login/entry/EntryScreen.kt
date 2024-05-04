package com.cook_and_share.presentation.ui.screens.login.entry

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.theme.Typography

@Composable
fun EntryScreen(
    navigate: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    viewModel: EntryViewModel = hiltViewModel()
) {
    EntryScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        onLogInClick = { viewModel.onLoginClick(openAndPopUp) },
        onGetStarted = { viewModel.onGetStartedClick(navigate) }
    )
}

@Composable
private fun EntryScreenContent(
    onLogInClick: () -> Unit,
    onGetStarted: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(

        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            ImageContent(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(5f),
            )
            ButtonContent(
                modifier = Modifier
                    .weight(1f),
                onLogInClick = onLogInClick,
                onGetStarted = onGetStarted
            )
        }
    }
}

@Composable
private fun ImageContent(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
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

@Composable
private fun ButtonContent(
    onLogInClick: () -> Unit,
    onGetStarted: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SecondaryButton(
            modifier = Modifier
                .padding(horizontal = 70.dp)
                .height(65.dp)
                .fillMaxWidth(),
            label = R.string.get_started,
            onClick = {
                onGetStarted()
            }
        )

        LoginText(
            onClick = {
                onLogInClick()
            }
        )
    }
}

@Composable
private fun LoginText(onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = stringResource(id = R.string.have_account),
            color = colorScheme.onPrimary,
        )

        Text(
            text = stringResource(id = R.string.login),
            color = colorScheme.onPrimary,
            style = Typography.titleMedium,
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable { onClick() },
        )
    }
}