package com.mealmentor.presentation.authentication

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mealmentor.R
import com.mealmentor.data.Resource
import com.mealmentor.presentation.custom.CustomTextField
import com.mealmentor.presentation.custom.PasswordField
import com.mealmentor.ui.theme.ButtonText
import com.mealmentor.ui.theme.MainTitle
import com.mealmentor.ui.theme.SmallTitle
import com.mealmentor.ui.theme.SmallTitleBold
import com.mealmentor.util.Screens

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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MainTitle
            )
            Text(
                text = stringResource(id = R.string.login_to_acc),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = SmallTitle
            )
        }

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CustomTextField(
                fieldLabel = stringResource(id = R.string.email),
                text = emailText,
                icon = Icons.Default.Email,
                onValueChange = {
                    emailText = it
                }
            )

            PasswordField(
                fieldLabel = stringResource(id = R.string.password),
                text = passwordText,
                onValueChange = {
                    passwordText = it
                }
            )
            Text(
                text = stringResource(id = R.string.forgot_password),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = SmallTitleBold,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        //navigateToForgotPasswordPage()
                    }
            )

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
                modifier = Modifier
                    .padding(horizontal = 80.dp)
                    .height(65.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = ButtonText
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = stringResource(id = R.string.dont_have_account),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = SmallTitle
                )

                Text(
                    text = stringResource(id = R.string.sign_up),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = SmallTitleBold,
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

        ElevatedButton(
            onClick = {
                //gfbgfbgfbgfbgfbfg
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(65.dp),
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
                    style = ButtonText,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
        loginFlow?.value?.let {
            when (it) {
                is Resource.Error -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                Resource.Loading -> {
                    CircularProgressIndicator()
                }
                is Resource.Success -> {
                    LaunchedEffect(Unit){
                        navController.navigate(route = Screens.Main.route) {
                            popUpTo(Screens.LoginScreen.route) { inclusive = true }
                        }
                    }
                }
            }
        }
    }
}
