package com.cook_and_share.presentation.ui.screens.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.AuthTitle
import com.cook_and_share.presentation.ui.components.EmailField
import com.cook_and_share.presentation.ui.components.PasswordField
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.theme.CookAndShareTheme
import com.cook_and_share.presentation.ui.theme.Typography

// LoginPage це функція, яка містить розмітку сторінки входу в додаток
@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    LoginScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = { viewModel.onSignInClick(openAndPopUp) },
        onForgotPasswordClick = { viewModel.onForgotPasswordClick() },
        onSignUpClick = { viewModel.onSignUpClick(openAndPopUp) }
    )

}

@Composable
private fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        AuthTitle(
            subTitle = R.string.login_to_acc,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        )

        Column(
            Modifier.fillMaxWidth(),
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

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

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

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            SecondaryButton(
                modifier = Modifier
                    .padding(horizontal = 80.dp)
                    .height(65.dp)
                    .fillMaxWidth(),
                label = R.string.login,
                onClick = onSignInClick
            )

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            SignUpText(onSignUpClick)
        }

        GoogleButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(65.dp)
        )
    }
}

@Composable
private fun SignUpText(onClick: () -> Unit){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = stringResource(id = R.string.dont_have_account),
            color = MaterialTheme.colorScheme.onPrimary,
        )

        Text(
            text = stringResource(id = R.string.sign_up),
            color = MaterialTheme.colorScheme.onPrimary,
            style = Typography.titleMedium,
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable { onClick() }
        )
    }
}

@Composable
private fun GoogleButton(modifier: Modifier) {
    ElevatedButton(
        onClick = {
            //TODO: Implement Google Sign In
        },
        shape = ButtonDefaults.elevatedShape,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 3.dp,
            pressedElevation = 0.dp
        ),
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "google_img",
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = stringResource(id = R.string.login_with_google),
                style = Typography.labelLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val uiState = LoginUiState(
        email = "email@test.com"
    )

    CookAndShareTheme {
        LoginScreenContent(
            uiState = uiState,
            onEmailChange = { },
            onPasswordChange = { },
            onSignInClick = { },
            onForgotPasswordClick = { },
            onSignUpClick = { }
        )
    }
}