package com.mealmentor.logic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.mealmentor.R
import com.mealmentor.ui.pages.screens.Screens

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
            route = Screens.Home.name,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.home),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.home_nonactive),
            hasNews = true
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.add),
            route = Screens.AddRecipe.name,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.plus),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.plus_nonactive),
            hasNews = false
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.search),
            route = Screens.Search.name,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.search),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.search_nonactive),
            hasNews = false
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.profile),
            route = Screens.Profile.name,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.profile),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.profile_nonactive),
            hasNews = false,
            badgeCount = 45
        ),
    )

}