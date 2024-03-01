package com.mealmentor.presentation.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mealmentor.R
import com.mealmentor.presentation.authentication.AuthViewModel
import com.mealmentor.presentation.custom.getBottomNavigationItems
import com.mealmentor.util.Screens
import com.mealmentor.presentation.custom.getDrawerItems
import com.mealmentor.presentation.main.screens.add_recipe.AddRecipeScreen
import com.mealmentor.presentation.main.screens.home.HomeScreen
import com.mealmentor.presentation.main.screens.profile.ProfileScreen
import com.mealmentor.presentation.main.screens.search.SearchScreen
import com.mealmentor.presentation.main.screens.profile.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    viewModel: AuthViewModel?,
    localNavController: NavHostController
) {
    val profileViewModel = hiltViewModel<ProfileViewModel>()

    val navController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val currentText = remember { mutableIntStateOf(R.string.app_name) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    DrawerBar(viewModel, localNavController,scope, drawerState) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopBar(currentText.intValue, scope, drawerState, scrollBehavior)
            },
            bottomBar = {
                NavigationBar(navController)
            }
        ) { values ->
            NavHost(
                navController = navController,
            startDestination = Screens.HomeScreen.route,
            modifier = Modifier
                .padding(values)
            ) {
            composable(Screens.HomeScreen.route) {
                currentText.intValue = R.string.app_name
                HomeScreen()
            }
            composable(Screens.AddRecipeScreen.route) {
                currentText.intValue = R.string.preview
                AddRecipeScreen()
            }
            composable(Screens.SearchRecipeScreen.route) {
                currentText.intValue = R.string.search
                SearchScreen()
            }
            composable(Screens.ProfileScreen.route) {
                currentText.intValue = R.string.profile
                ProfileScreen(profileViewModel)
            }
        }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    text: Int,
    scope: CoroutineScope,
    drawerState: DrawerState,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        modifier = Modifier.shadow(elevation = 3.dp),
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Text(
                text = stringResource(id = text),
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NavigationBar(navController: NavController) {
    NavigationBar(
        modifier = Modifier.shadow(elevation = 3.dp),
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        getBottomNavigationItems().forEachIndexed { _, item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    //Here can be a navController
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                    indicatorColor = MaterialTheme.colorScheme.onSecondary
                ),
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge (
                                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                                    contentColor = MaterialTheme.colorScheme.primary
                                ){
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews) {
                                Badge(containerColor = MaterialTheme.colorScheme.inversePrimary)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (currentDestination?.hierarchy?.any { it.route == item.route } == true)
                                item.selectedIcon
                            else item.unselectedIcon,
                            contentDescription = item.title,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun DrawerBar(
    viewModel: AuthViewModel?,
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape,
                drawerContainerColor = MaterialTheme.colorScheme.primary
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                getDrawerItems().forEachIndexed { index, drawerItem ->

                    Spacer(modifier = Modifier.height(4.dp))

                    NavigationDrawerItem(
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            unselectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.inversePrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            selectedIconColor = MaterialTheme.colorScheme.inversePrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedBadgeColor = MaterialTheme.colorScheme.inversePrimary,
                            unselectedBadgeColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        label = { Text(drawerItem.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            //Тут має бути навігація
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    drawerItem.selectedIcon
                                } else drawerItem.unselectedIcon,
                                contentDescription = drawerItem.title
                            )
                        },
                        badge = {
                            drawerItem.badgeCount?.let {
                                Text(text = drawerItem.badgeCount.toString())
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = MaterialTheme.colorScheme.tertiary,
                        unselectedContainerColor = MaterialTheme.colorScheme.tertiary,
                        selectedTextColor = MaterialTheme.colorScheme.onTertiary,
                        unselectedTextColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.sign_out),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    selected = false,
                    onClick = {
                        viewModel?.logout()
                        navController.navigate(Screens.LoginScreen.route) {
                            popUpTo(Screens.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        },
        drawerState = drawerState,
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(){
    ModalBottomSheet(
        onDismissRequest = {

        }
    ) {

    }
}
