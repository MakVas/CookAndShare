package com.mealmentor.ui.pages.screens.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mealmentor.R
import com.mealmentor.ui.pages.screens.elements.CustomTextField
import com.mealmentor.ui.pages.screens.elements.PasswordField
import com.mealmentor.ui.theme.ButtonText
import com.mealmentor.ui.theme.SmallTitle
import com.mealmentor.ui.theme.SmallTitleBold

// LoginPage це функція, яка містить розмітку сторінки входу в додаток
@Composable
fun LoginPage(
    context: Context,
    navigateToForgotPasswordPage: () -> Unit,
    navigateToSignUpPage: () -> Unit
) {

    // Дві функції remember для збереження стану текстового поля
    val emailText = remember {
        mutableStateOf("")
    }

    val passwordText = remember {
        mutableStateOf("")
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CustomTextField(
            fieldLabel = stringResource(id = R.string.email),
            text = emailText.value,
            leadingIconId = R.drawable.email,
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

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        Text(
            text = stringResource(id = R.string.forgot_password),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            style = SmallTitleBold,
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable {
                    navigateToForgotPasswordPage()
                }
        )

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

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
                text = stringResource(id = R.string.login),
                style = ButtonText
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

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
                        navigateToSignUpPage()
                    }
            )
        }

    }
}
