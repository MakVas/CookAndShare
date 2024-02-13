package com.mealmentor.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.mealmentor.R
import com.mealmentor.util.Screens

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
            selectedIcon = ImageVector.vectorResource(id = R.drawable.home),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.home_nonactive),
            hasNews = true
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.add),
            route = Screens.AddRecipeScreen.route,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.plus),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.plus_nonactive),
            hasNews = false
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.search),
            route = Screens.SearchRecipeScreen.route,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.search),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.search_nonactive),
            hasNews = false
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.profile),
            route = Screens.ProfileScreen.route,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.profile),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.profile_nonactive),
            hasNews = false,
            badgeCount = 45
        ),
    )
}