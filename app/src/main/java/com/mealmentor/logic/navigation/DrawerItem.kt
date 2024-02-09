package com.mealmentor.logic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.mealmentor.R

data class DrawerItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)

@Composable
fun getDrawerItems(): List<DrawerItem> {
    return listOf(
        DrawerItem(
            title = stringResource(id = R.string.home),
            route = NavigationRoutes.Home.name,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.home),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.home_nonactive)
        ),
        DrawerItem(
            title = stringResource(id = R.string.settings),
            route = NavigationRoutes.Settings.name,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.settings),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.settings_nonactive)
        ),
        DrawerItem(
            title = stringResource(id = R.string.info),
            route = NavigationRoutes.Info.name,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.info),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.info_nonactive),
            badgeCount = 23
        ),
    )

}