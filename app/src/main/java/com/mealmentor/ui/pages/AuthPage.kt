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
import com.mealmentor.logic.database.sign_in.FirebaseViewModel
import com.mealmentor.logic.database.sign_in.SignInState
import com.mealmentor.logic.navigation.NavigationRoutes
import com.mealmentor.ui.pages.screens.auth.LoginPage
import com.mealmentor.ui.pages.screens.auth.SignUpPage
import com.mealmentor.ui.theme.ButtonText
import com.mealmentor.ui.theme.MainTitle
import com.mealmentor.ui.theme.SmallTitle

@Composable
fun AuthPage(
   // navController: NavHostController,
    state: SignInState,
    viewModel: FirebaseViewModel,
    onGoogleSignInClick: () -> Unit,
    onMainNavigation: () -> Unit
) {
    // Отримання контексту
    val context = LocalContext.current
    val navController = rememberNavController()
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

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
            navController = navController, startDestination = NavigationRoutes.Login.name
        ) {
            composable(NavigationRoutes.Login.name) {
                LoginPage(
                    //context = context,
                    viewModel = viewModel,
                    navigateToForgotPasswordPage = {
                        currentText.intValue = R.string.forgot_password
                        navController.popBackStack()
                        navController.navigate(NavigationRoutes.ForgotPassword.name)
                    },
                    navigateToSignUpPage = {
                        currentText.intValue = R.string.sign_up_text
                        navController.popBackStack()
                        navController.navigate(NavigationRoutes.SignUp.name)
                    },
                    navigateToMainPage = {
                        onMainNavigation()
                    }
                )
            }
            composable(NavigationRoutes.SignUp.name) {
                SignUpPage(
                    //context = context,
                    viewModel = viewModel,
                    navigateToLogInPage = {
                        currentText.intValue = R.string.login_to_acc
                        navController.popBackStack()
                        navController.navigate(NavigationRoutes.Login.name)
                    },
                    navigateToMainPage = {
                        onMainNavigation()
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
    }
}