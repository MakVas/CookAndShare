package com.cook_and_share.auth.presentation

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cook_and_share.R
import com.cook_and_share.core.presentation.ui.components.AuthTitle
import com.cook_and_share.core.presentation.util.Resource
import com.cook_and_share.core.presentation.ui.components.CustomTextField
import com.cook_and_share.core.presentation.ui.components.PasswordField
import com.cook_and_share.core.presentation.ui.theme.Typography
import com.cook_and_share.core.presentation.util.Screens

// LoginPage це функція, яка містить розмітку сторінки входу в додаток
@Composable
fun LoginScreen(
    viewModel: AuthViewModel?,
    navController: NavHostController
) {

    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    val loginFlow = viewModel?.loginFlow?.collectAsState()

    Column(
        modifier = Modifier
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

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .shadow(1.dp, RoundedCornerShape(16.dp), clip = true),
                fieldLabel = stringResource(id = R.string.email),
                text = emailText,
                icon = Icons.Default.Email
            ) {
                emailText = it
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            PasswordField(
                fieldLabel = stringResource(id = R.string.password),
                text = passwordText,
                onValueChange = {
                    passwordText = it
                }
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = stringResource(id = R.string.forgot_password),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = Typography.titleMedium,
                modifier = Modifier
                    .clickable {
                        navController.navigate(route = Screens.ForgotPassScreen.route) {
                            launchSingleTop = true
                        }
                    }
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            LogInButton(
                modifier = Modifier
                    .padding(horizontal = 80.dp)
                    .height(65.dp)
                    .fillMaxWidth(),
                viewModel = viewModel,
                emailText = emailText,
                passwordText = passwordText
            )

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            SignUpText(navController = navController)
        }

        GoogleButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(65.dp)
        )

        loginFlow?.value?.let {
            when (it) {
                is Resource.Error -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }

                Resource.Loading -> {
                }

                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(route = Screens.Main.route) {
                            popUpTo(Screens.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LogInButton(
    modifier: Modifier,
    viewModel: AuthViewModel?,
    emailText: String,
    passwordText: String
) {
    ElevatedButton(
        onClick = {
            viewModel?.login(emailText, passwordText)
        },
        shape = ButtonDefaults.elevatedShape,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 3.dp,
            pressedElevation = 0.dp
        ),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.login),
            style = Typography.labelLarge
        )
    }
}

@Composable
private fun SignUpText(navController: NavHostController) {
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
                .clickable {
                    navController.navigate(route = Screens.SignUpScreen.route) {
                        launchSingleTop = true
                    }
                }
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