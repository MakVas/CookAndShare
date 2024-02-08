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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
import com.mealmentor.ui.pages.screens.Screens
import com.mealmentor.ui.pages.screens.auth.LoginPage
import com.mealmentor.ui.pages.screens.auth.SignUpPage
import com.mealmentor.ui.theme.ButtonText
import com.mealmentor.ui.theme.MainTitle
import com.mealmentor.ui.theme.SmallTitle

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
    val currentText = remember { mutableIntStateOf(R.string.login_to_acc) }
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
                text = stringResource(id = currentText.intValue),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = SmallTitle
            )
        }

        //тут має бути викликано скрін входу/авторизації
        NavHost(
            navController = navController, startDestination = Screens.Login.name
        ) {
            composable(Screens.Login.name) {
                LoginPage(
                    context = context,
                    navigateToForgotPasswordPage = {
                        currentText.intValue = R.string.forgot_password
                        navController.popBackStack()
                        navController.navigate(Screens.ForgotPassword.name)
                    },
                    navigateToSignUpPage = {
                        currentText.intValue = R.string.sign_up_text
                        navController.popBackStack()
                        navController.navigate(Screens.SignUp.name)
                    }
                )
            }
            composable(Screens.SignUp.name) {
                SignUpPage(
                    context = context,
                    navigateToLogInPage = {
                        currentText.intValue = R.string.login_to_acc
                        navController.popBackStack()
                        navController.navigate(Screens.Login.name)
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
                    style = ButtonText,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}