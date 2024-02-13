package com.mealmentor.logic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.mealmentor.R
import com.mealmentor.util.Screens

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
            route = Screens.HomeScreen.route,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.home),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.home_nonactive)
        ),
        DrawerItem(
            title = stringResource(id = R.string.settings),
            route = Screens.Settings.route,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.settings),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.settings_nonactive)
        ),
        DrawerItem(
            title = stringResource(id = R.string.info),
            route = Screens.Info.route,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.info),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.info_nonactive),
            badgeCount = 23
        ),
    )

}