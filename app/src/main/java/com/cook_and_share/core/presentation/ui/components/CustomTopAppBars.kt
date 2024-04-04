package com.cook_and_share.core.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cook_and_share.core.presentation.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    text: Int,
    scrollBehavior: TopAppBarScrollBehavior,
    iconButton: @Composable () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.shadow(elevation = 3.dp),
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = colorScheme.onPrimary
        ),
        title = {
            Text(
                text = stringResource(id = text),
                style = Typography.titleLarge
            )
        },
        navigationIcon = {
            iconButton()
        },
    )
}

@Composable
fun SearchTopBar(
    text: Int,
    tabIndex: MutableState<Int>,
    tabs: List<String>,
    searchQuery: MutableState<String>
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 3.dp
    ) {
        Column {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){

                CustomTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp, top = 4.dp)
                        .shadow(1.dp, RoundedCornerShape(16.dp), clip = true),
                    icon = Icons.Filled.Search,
                    fieldLabel = stringResource(id = text),
                    text = searchQuery.value,
                ) {
                    searchQuery.value = it
                }

                IconButton(
                    modifier = Modifier.padding(top = 4.dp,end = 4.dp),
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Filled.FilterList,
                        contentDescription = "filter"
                    )
                }
            }

            TabRow(
                selectedTabIndex = tabIndex.value,
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary,
                divider = { },
                indicator = { tabPositions ->
                    if (tabIndex.value < tabPositions.size) {
                        SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex.value]),
                            height = 1.dp,
                            color = colorScheme.onPrimary
                        )
                    }
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex.value == index,
                        onClick = { tabIndex.value = index }
                    )
                }
            }
        }
    }
}

@Composable
fun TopAppBarMenuIcon(
    scope: CoroutineScope,
    drawerState: DrawerState
){
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
            tint = colorScheme.onPrimary
        )
    }
}

@Composable
fun TopAppBarBackIcon(
    navController: NavHostController
){
    IconButton(
        onClick = {
            navController.popBackStack()
        }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = colorScheme.onPrimary
        )
    }
}