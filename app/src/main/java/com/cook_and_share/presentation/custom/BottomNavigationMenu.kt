package com.cook_and_share.presentation.custom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.cook_and_share.R
import com.cook_and_share.util.Screens

data class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@Composable
fun getBottomNavigationItems(): List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(
            title = stringResource(id = R.string.home),
            route = Screens.HomeScreen.route,
            selectedIcon = Icons.Default.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.add),
            route = Screens.AddRecipeScreen.route,
            selectedIcon = Icons.Default.Add,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.search),
            route = Screens.SearchRecipeScreen.route,
            selectedIcon = Icons.Default.Search,
            unselectedIcon = Icons.Outlined.Search,
            hasNews = false
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.profile),
            route = Screens.ProfileScreen.route,
            selectedIcon = Icons.Default.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            hasNews = false
        ),
    )
}