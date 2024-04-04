package com.cook_and_share.profile.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cook_and_share.R
import com.cook_and_share.core.domain.model.User
import com.cook_and_share.core.presentation.ui.components.PrimaryButton
import com.cook_and_share.core.presentation.ui.components.SecondaryButton
import com.cook_and_share.core.presentation.ui.components.TopBar
import com.cook_and_share.core.presentation.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    navController: NavHostController,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                text = R.string.profile,
                scrollBehavior = scrollBehavior,
            )
        }
    ) { values ->
        Box(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            NestedScrolling(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    viewModel: ProfileViewModel,
    navController: NavHostController
) {

    val getUserData = viewModel.user.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        ProfileContent(
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            getUserData = getUserData
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            label = R.string.settings,
            icon = Icons.Outlined.Settings,
            onClick = {
                navController.navigate(Screens.Settings.route) {
                    popUpTo(Screens.Settings.route) {
                        inclusive = false
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            label = R.string.info,
            icon = Icons.Outlined.Info,
            onClick = {
                navController.navigate(Screens.Info.route) {
                    popUpTo(Screens.Info.route) {
                        inclusive = false
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SecondaryButton(
            modifier = Modifier
                .height(65.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            label = R.string.sign_out,
            onClick = {
               //TODO: viewModel.logout()

//                navController.navigate(Screens.SignUpScreen.route) {
//                    popUpTo(Screens.SignUpScreen.route) {
//                        inclusive = true
//                    }
//                }
            }
        )
    }
}

@Composable
private fun ProfileContent(modifier: Modifier, getUserData: User) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = getUserData.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Text(
                text = getUserData.email,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
    }
}