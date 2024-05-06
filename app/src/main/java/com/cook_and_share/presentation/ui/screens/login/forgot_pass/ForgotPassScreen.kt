package com.cook_and_share.presentation.ui.screens.login.forgot_pass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.AuthTitle
import com.cook_and_share.presentation.ui.components.EmailField
import com.cook_and_share.presentation.ui.components.SecondaryButton

@Composable
fun ForgotPassScreen(
    popUp: () -> Unit,
    viewModel: ForgotPassViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    ForgotPassScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onForgotPasswordClick = { viewModel.onForgotPasswordClick(popUp) }
    )

}

@Composable
private fun ForgotPassScreenContent(
    uiState: ForgotPassUiState,
    onEmailChange: (String) -> Unit,
    onForgotPasswordClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        AuthTitle(
            subTitle = R.string.forgot_pass_title,
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

            SecondaryButton(
                modifier = Modifier
                    .padding(horizontal = 70.dp)
                    .height(65.dp)
                    .fillMaxWidth(),
                label = R.string.send,
                onClick = onForgotPasswordClick
            )
        }
    }
}