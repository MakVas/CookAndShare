package com.cook_and_share.core.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.cook_and_share.R
import com.cook_and_share.core.presentation.util.Screens

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
            route = Screens.Main.route,
            selectedIcon = Icons.Default.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        DrawerItem(
            title = stringResource(id = R.string.settings),
            route = Screens.Settings.route,
            selectedIcon = Icons.Default.Settings,
            unselectedIcon = Icons.Outlined.Settings
        ),
        DrawerItem(
            title = stringResource(id = R.string.info),
            route = Screens.Info.route,
            selectedIcon = Icons.Default.Info,
            unselectedIcon = Icons.Outlined.Info,
            badgeCount = 23
        ),
    )

}