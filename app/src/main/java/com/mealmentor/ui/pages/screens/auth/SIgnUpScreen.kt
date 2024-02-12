package com.mealmentor.ui.pages.screens.auth

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mealmentor.R
import com.mealmentor.logic.database.sign_in.FirebaseViewModel
import com.mealmentor.ui.pages.screens.elements.CustomTextField
import com.mealmentor.ui.pages.screens.elements.PasswordField
import com.mealmentor.ui.theme.ButtonText
import com.mealmentor.ui.theme.SmallTitle
import com.mealmentor.ui.theme.SmallTitleBold

// LoginPage це функція, яка містить розмітку сторінки входу в додаток
@Composable
fun SignUpPage(
    //context: Context,
    viewModel: FirebaseViewModel,
    navigateToLogInPage: () -> Unit,
    navigateToMainPage: () -> Unit
) {

    var usernameText by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var password2Text by remember { mutableStateOf("") }
    var errorUsername by remember { mutableStateOf(false) }
    var errorEmail by remember { mutableStateOf(false) }
    var errorPassword by remember { mutableStateOf(false) }
    var errorPassword2 by remember { mutableStateOf(false) }
    var errorMatchPassword by remember { mutableStateOf(false) }
    var passwordLength by remember { mutableStateOf(false) }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (viewModel.inProgress.value) {
            CircularProgressIndicator()
        }

        if (errorUsername) {
            Text(
                text = stringResource(id = R.string.username_error),
                color = MaterialTheme.colorScheme.error,
                style = SmallTitle
            )
        }

        CustomTextField(
            fieldLabel = stringResource(id = R.string.username),
            leadingIconId = R.drawable.user,
            text = usernameText,
            onValueChange = {
                usernameText = it
            }
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        if (errorEmail) {
            Text(
                text = stringResource(id = R.string.email_error),
                color = MaterialTheme.colorScheme.error,
                style = SmallTitle
            )
        }

        CustomTextField(
            fieldLabel = stringResource(id = R.string.email),
            leadingIconId = R.drawable.email,
            text = emailText,
            onValueChange = {
                emailText = it
            }
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        if (errorPassword) {
            Text(
                text = stringResource(id = R.string.password_error),
                color = MaterialTheme.colorScheme.error,
                style = SmallTitle
            )
        }

        if (passwordLength) {
            Text(
                text = stringResource(id = R.string.password_length_error),
                color = MaterialTheme.colorScheme.error,
                style = SmallTitle
            )
        }

        PasswordField(
            fieldLabel = stringResource(id = R.string.password),
            text = passwordText,
            onValueChange = {
                passwordText = it
                passwordLength = it.length < 6
            }
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        if (errorPassword2) {
            Text(
                text = stringResource(id = R.string.password2_error),
                color = MaterialTheme.colorScheme.error,
                style = SmallTitle
            )
        }

        if (errorMatchPassword) {
            Text(
                text = stringResource(id = R.string.password_match_error),
                color = MaterialTheme.colorScheme.error,
                style = SmallTitle
            )
        }

        PasswordField(
            fieldLabel = stringResource(id = R.string.confirm_password),
            text = password2Text,
            onValueChange = {
                password2Text = it
                passwordLength = it.length < 6
            }
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        ElevatedButton(
            onClick = {
                if (usernameText.isNotEmpty()) {
                    errorUsername = false
                    if (emailText.isNotEmpty()) {
                        errorEmail = false
                        if (passwordText.isNotEmpty()) {
                            errorPassword = false
                            if (password2Text.isNotEmpty()) {
                                errorPassword2 = false
                                if (passwordText == password2Text) {
                                    errorMatchPassword = false
                                    if (passwordText.length >= 6) {
                                        passwordLength = false
                                        viewModel.onSignUp(emailText, passwordText)
                                    } else {
                                        passwordLength = true
                                    }
                                } else {
                                    errorMatchPassword = true
                                }
                            } else {
                                errorPassword2 = true
                            }
                        } else {
                            errorPassword = true
                        }
                    } else {
                        errorEmail = true
                    }
                }
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
                text = stringResource(id = R.string.sign_up),
                style = ButtonText
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        Row(
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(id = R.string.have_account),
                color = MaterialTheme.colorScheme.onPrimary,
                style = SmallTitle
            )

            Text(
                text = stringResource(id = R.string.login),
                color = MaterialTheme.colorScheme.onPrimary,
                style = SmallTitleBold,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        navigateToLogInPage()
                    }
            )

            if (viewModel.signedIn.value) {
                navigateToMainPage()
            }
            viewModel.signedIn.value = false
        }
    }

}
