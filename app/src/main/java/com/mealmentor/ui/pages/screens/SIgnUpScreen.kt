package com.mealmentor.ui.pages.screens

import android.content.Context
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mealmentor.R
import com.mealmentor.logic.database.sign_in.SignInState
import com.mealmentor.ui.pages.screens.elements.CustomTextField
import com.mealmentor.ui.pages.screens.elements.PasswordField

// LoginPage це функція, яка містить розмітку сторінки входу в додаток
@Composable
fun SignUpPage(
    context: Context,
    navigateToLogInPage: () -> Unit
) {

    // Чотири функції remember для збереження стану текстового поля
    val usernameText = remember {
        mutableStateOf("")
    }

    val emailText = remember {
        mutableStateOf("")
    }

    val passwordText = remember {
        mutableStateOf("")
    }

    val password2Text = remember {
        mutableStateOf("")
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CustomTextField(
            fieldLabel = stringResource(id = R.string.username),
            leadingIconId = R.drawable.user,
            text = usernameText.value,
            onValueChange = {
                usernameText.value = it
            }
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        CustomTextField(
            fieldLabel = stringResource(id = R.string.email),
            leadingIconId = R.drawable.email,
            text = emailText.value,
            onValueChange = {
                emailText.value = it
            }
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        PasswordField(
            fieldLabel = stringResource(id = R.string.password),
            text = passwordText.value,
            onValueChange = {
                passwordText.value = it
            }
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        PasswordField(
            fieldLabel = stringResource(id = R.string.confirm_password),
            text = password2Text.value,
            onValueChange = {
                password2Text.value = it
            }
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        ElevatedButton(
            onClick = {
                Toast.makeText(
                    context,
                    "Login",
                    Toast.LENGTH_SHORT
                ).show()
            },
            shape = ButtonDefaults.elevatedShape,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 0.dp
            ),
            modifier = Modifier
                .padding(horizontal = 80.dp)
                .height(65.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.labelLarge
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        Row(
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(id = R.string.have_account),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelSmall
            )

            Text(
                text = stringResource(id = R.string.login),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        navigateToLogInPage()
                    }
            )
        }

    }


}
