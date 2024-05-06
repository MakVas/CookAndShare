package com.cook_and_share.presentation.ui.screens.login.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.AuthTitle
import com.cook_and_share.presentation.ui.components.EmailField
import com.cook_and_share.presentation.ui.components.PasswordField
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.theme.Typography

// LoginPage це функція, яка містить розмітку сторінки входу в додаток
@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    navigate: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    LoginScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = { viewModel.onSignInClick(openAndPopUp) },
        onForgotPasswordClick = { viewModel.onForgotPasswordClick(navigate) },
        onSignUpClick = { viewModel.onSignUpClick(navigate) }
    )

}

@Composable
private fun LoginScreenContent(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        AuthTitle(
            subTitle = R.string.login_to_acc,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            EmailField(
                value = uiState.email,
                onNewValue = onEmailChange
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            PasswordField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                label = stringResource(id = R.string.password)
            )

            Spacer(modifier = Modifier.padding(vertical = 6.dp))

            Text(
                text = stringResource(id = R.string.forgot_password),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = Typography.titleMedium,
                modifier = Modifier
                    .clickable {
                        onForgotPasswordClick()
                    }
            )

            Spacer(modifier = Modifier.padding(vertical = 6.dp))

            SecondaryButton(
                modifier = Modifier
                    .padding(horizontal = 70.dp)
                    .height(65.dp)
                    .fillMaxWidth(),
                label = R.string.login,
                onClick = onSignInClick
            )

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            GetStartedText(onSignUpClick)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GetStartedText(onClick: () -> Unit) {
    FlowRow(
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.dont_have_account),
            color = MaterialTheme.colorScheme.onPrimary,
        )

        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.sign_up),
            color = MaterialTheme.colorScheme.onPrimary,
            style = Typography.titleMedium,
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable { onClick() }
        )
    }
}