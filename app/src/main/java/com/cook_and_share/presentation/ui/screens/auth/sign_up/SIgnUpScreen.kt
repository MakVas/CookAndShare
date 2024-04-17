package com.cook_and_share.presentation.ui.screens.auth.sign_up

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.AuthTitle
import com.cook_and_share.presentation.ui.components.CustomTextField
import com.cook_and_share.presentation.ui.components.EmailField
import com.cook_and_share.presentation.ui.components.PasswordField
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.theme.Typography

// LoginPage це функція, яка містить розмітку сторінки входу в додаток
@Composable
fun SignUpScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    SignUpScreenContent(
        uiState = uiState,
        onUsernameChange = viewModel::onUsernameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
        onSignUpClick = { viewModel.onSignUpClick(openAndPopUp) },
        onLoginClick = { viewModel.onLoginClick(openAndPopUp) }
    )
}

@Composable
private fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    uiState: SignUpUiState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AuthTitle(
            subTitle = R.string.sign_up_text,
            modifier = Modifier
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomTextField(
                icon = Icons.Default.Person,
                fieldLabel = stringResource(id = R.string.username),
                value = uiState.username,
                onValueChange = onUsernameChange
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

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

            PasswordField(
                value = uiState.repeatPassword,
                onValueChange = onRepeatPasswordChange,
                label = stringResource(id = R.string.repeat_password)
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            SecondaryButton(
                modifier = Modifier
                    .padding(horizontal = 80.dp)
                    .height(65.dp)
                    .fillMaxWidth(),
                label = R.string.sign_up,
                onClick = onSignUpClick
            )

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            LoginText(onLoginClick)
        }
    }
}

@Composable
private fun LoginText(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(id = R.string.have_account),
            color = MaterialTheme.colorScheme.onPrimary,
        )

        Text(
            text = stringResource(id = R.string.login),
            color = MaterialTheme.colorScheme.onPrimary,
            style = Typography.titleMedium,
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable { onClick() }
        )
    }
}