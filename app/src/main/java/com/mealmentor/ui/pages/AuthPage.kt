package com.mealmentor.ui.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mealmentor.R
import com.mealmentor.logic.database.sign_in.SignInState
import com.mealmentor.ui.pages.screens.LoginPage
import com.mealmentor.ui.pages.screens.SignUpPage

@Composable
fun AuthPage(
    state: SignInState,
    onGoogleSignInClick: () -> Unit,
){
    // Отримання контексту
    val context = LocalContext.current

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    //тут має бути логіка перемикання між скрінами
    val navController = rememberNavController()
    val currentRoute = navController.currentDestination?.route
    val text = if (currentRoute == "sign_up") {
        stringResource(id = R.string.sign_up_text)
    } else {
        stringResource(id = R.string.login_to_acc)
    }


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
                text = "Meal Mentor",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = text,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelSmall
            )
        }

        //тут має бути викликано скрін входу/авторизації
        NavHost(
            navController = navController, startDestination = "login"
        ) {
            composable("login") {
                LoginPage(
                    context = context,
                    navigateToForgotPasswordPage = {
                        navController.popBackStack()
                        navController.navigate("forgot_password")
                    },
                    navigateToSignUpPage = {
                        navController.popBackStack()
                        navController.navigate("sign_up")
                    }
                )
            }
            composable("sign_up") {
                SignUpPage(
                    context = context,
                    navigateToLogInPage = {
                        navController.popBackStack()
                        navController.navigate("login")
                    }
                )
            }
        }

        // Кнопка увійти з гугл
        ElevatedButton(
            onClick = {
                onGoogleSignInClick()
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
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}