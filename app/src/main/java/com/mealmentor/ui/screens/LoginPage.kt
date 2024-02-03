package com.mealmentor.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mealmentor.R
import com.mealmentor.ui.screens.elements.EmailField
import com.mealmentor.ui.screens.elements.PasswordField

// LoginPage це функція, яка містить розмітку сторінки входу в додаток
@Composable
fun LoginPage() {

    // Отримання контексту
    val context = LocalContext.current

    // Дві функції remember для збереження стану текстового поля
    val emailText = remember {
        mutableStateOf("")
    }

    val passwordText = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Виклик функцій для відображення текстових полів
        EmailField(
            text = emailText.value,
            onValueChange = {
                emailText.value = it
            }
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        PasswordField(
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
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable {
                    Toast
                        .makeText(
                            context,
                            "Forgot Password",
                            Toast.LENGTH_SHORT
                        )
                        .show()
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
                style = MaterialTheme.typography.labelLarge
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        Row(
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(id = R.string.dont_have_account),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelSmall
            )

            Text(
                text = stringResource(id = R.string.sign_up),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        Toast
                            .makeText(
                                context,
                                "Sign Up",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
            )
        }

    }

    // Відображення тексту "Meal Mentor"
    Text(
        text = "Meal Mentor",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.titleLarge
    )

    // Кнопка "Увійти з Google"
    ElevatedButton(
        onClick = {
            Toast.makeText(
                context,
                "Google Button",
                Toast.LENGTH_SHORT
            ).show()
        },
        shape = ButtonDefaults.elevatedShape,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 0.dp
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(65.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.login_with_google),
            style = MaterialTheme.typography.labelLarge
        )
    }
}
