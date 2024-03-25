package com.cook_and_share.auth.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.res.stringResource
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
fun SignUpScreen(
    viewModel: AuthViewModel?,
    navController: NavHostController
) {
    var usernameText by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var password2Text by remember { mutableStateOf("") }
    var passwordLength by remember { mutableStateOf(false) }

    val signUpFlow = viewModel?.signupFlow?.collectAsState()


    Column(
        modifier = Modifier
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .shadow(1.dp, RoundedCornerShape(16.dp), clip = true),
                fieldLabel = stringResource(id = R.string.username),
                icon = Icons.Default.AccountCircle,
                text = usernameText
            ) {
                usernameText = it
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .shadow(1.dp, RoundedCornerShape(16.dp), clip = true),
                fieldLabel = stringResource(id = R.string.email),
                icon = Icons.Default.Email,
                text = emailText
            ) {
                emailText = it
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            PasswordField(
                fieldLabel = stringResource(id = R.string.password),
                text = passwordText,
                onValueChange = {
                    passwordText = it
                    passwordLength = it.length < 6
                }
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            PasswordField(
                fieldLabel = stringResource(id = R.string.confirm_password),
                text = password2Text,
                onValueChange = {
                    password2Text = it
                    passwordLength = it.length < 6
                }
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            SignUpButton(
                modifier = Modifier
                    .padding(horizontal = 80.dp)
                    .height(65.dp)
                    .fillMaxWidth(),
                viewModel = viewModel,
                emailText = emailText,
                passwordText = passwordText,
                usernameText = usernameText
            )

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            LoginText(navController = navController)

            signUpFlow?.value?.let {
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
                                popUpTo(Screens.LoginScreen.route) { inclusive = true }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SignUpButton(
    modifier: Modifier,
    viewModel: AuthViewModel?,
    emailText: String,
    passwordText: String,
    usernameText: String
) {
    ElevatedButton(
        onClick = {
            viewModel?.signup(emailText, passwordText, usernameText)
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
            text = stringResource(id = R.string.sign_up),
            style = Typography.labelLarge
        )
    }
}

@Composable
private fun LoginText(navController: NavHostController) {
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
                .clickable {
                    navController.navigate(route = Screens.LoginScreen.route) {
                        launchSingleTop = true
                    }
                }
        )
    }
}