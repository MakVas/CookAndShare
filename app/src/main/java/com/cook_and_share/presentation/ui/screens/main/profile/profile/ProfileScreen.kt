package com.cook_and_share.presentation.ui.screens.main.profile.profile

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.SettingsBottomSheet
import com.cook_and_share.presentation.ui.components.TopAppBarAction
import com.cook_and_share.presentation.ui.components.TopBar
import com.cook_and_share.presentation.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
   // viewModel: ProfileViewModel,
    navController: NavHostController,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val sheetState = rememberModalBottomSheetState()
    val isSheetExpanded = rememberSaveable { mutableStateOf(false) }
    SettingsBottomSheet(
        sheetState = sheetState,
        isSheetExpanded = isSheetExpanded,
        onSettingsClick = {
            isSheetExpanded.value = false
            navController.navigate(Screens.Settings.route) {
                popUpTo(Screens.Settings.route) {
                    inclusive = false
                }
            }
        },
        onLikedClick = {
            isSheetExpanded.value = false
            navController.navigate(Screens.Liked.route) {
                popUpTo(Screens.Liked.route) {
                    inclusive = false
                }
            }
        },
        onInfoClick = {
            isSheetExpanded.value = false
            navController.navigate(Screens.Info.route) {
                popUpTo(Screens.Info.route) {
                    inclusive = false
                }
            }
        }
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                text = R.string.profile,
                scrollBehavior = scrollBehavior,
                actions = {
                    TopAppBarAction(icon = Icons.Default.Menu) {
                        isSheetExpanded.value = true
                    }
                }
            )
        }
    ) { values ->
        Box(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
//            NestedScrolling(
//                viewModel = viewModel
//            )
        }
    }
}

@Composable
private fun NestedScrolling(
   // viewModel: ProfileViewModel
) {

   // val getUserData = viewModel.user.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

//        ProfileContent(
//            modifier = Modifier
//                .height(220.dp)
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp),
//            getUserData = getUserData
//        )
    }
}

//@Composable
//private fun ProfileContent(modifier: Modifier, getUserData: User) {
//    ElevatedCard(
//        modifier = modifier,
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.secondary,
//            contentColor = MaterialTheme.colorScheme.onSecondary
//        ),
//        elevation = CardDefaults.cardElevation(1.dp)
//    ) {
//        Column(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = getUserData.name,
//                style = MaterialTheme.typography.titleLarge,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .padding(top = 16.dp)
//            )
//            Text(
//                text = getUserData.email,
//                style = MaterialTheme.typography.titleLarge,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .padding(top = 8.dp)
//            )
//        }
//    }
//}