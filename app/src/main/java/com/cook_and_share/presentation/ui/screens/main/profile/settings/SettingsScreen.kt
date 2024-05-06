package com.cook_and_share.presentation.ui.screens.main.profile.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.outlined.FormatPaint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.components.SwitcherButton
import com.cook_and_share.presentation.ui.components.TopAppBarBackIcon
import com.cook_and_share.presentation.ui.components.TopBar

@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    toggleTheme: () -> Unit,
    popUp: () -> Unit,
    restartApp: (String) -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    SettingsScreenContent(
        isExitAccDialog = viewModel.isExitAccDialog,
        isDarkTheme = isDarkTheme,
        onThemeSwitchToggle = { toggleTheme() },
        popUp = popUp,
        onSignOutClick = { viewModel.onSignOutClick(restartApp) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreenContent(
    isExitAccDialog: MutableState<Boolean>,
    isDarkTheme: Boolean,
    onThemeSwitchToggle: () -> Unit,
    popUp: () -> Unit,
    onSignOutClick: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                text = R.string.settings,
                scrollBehavior = scrollBehavior,
                iconButton = {
                    TopAppBarBackIcon(popUp)
                }
            )
        }
    ) { values ->
        Box(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(colorScheme.background)
        ) {
            ExitAccDialog(
                isExitAccDialog = isExitAccDialog,
                onSignOutClick = onSignOutClick
            )
            NestedScrolling(
                isExitAccDialog = isExitAccDialog,
                isDarkTheme = isDarkTheme,
                onThemeSwitchToggle = onThemeSwitchToggle
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    isExitAccDialog: MutableState<Boolean>,
    isDarkTheme: Boolean,
    onThemeSwitchToggle: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.padding(top = 16.dp))

        Text(
            text = stringResource(id = R.string.application_design),
            style = typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.padding(top = 6.dp))

        SwitcherButton(
            modifier = Modifier
                .height(65.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            label = R.string.toggle_theme,
            icon = Icons.Outlined.FormatPaint,
            isActive = isDarkTheme,
            onSwitchToggle = onThemeSwitchToggle
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        SecondaryButton(
            modifier = Modifier
                .height(65.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            label = R.string.sign_out,
            onClick = {
                isExitAccDialog.value = true
            }
        )
    }
}

@Composable
private fun ExitAccDialog(
    modifier: Modifier = Modifier,
    isExitAccDialog: MutableState<Boolean>,
    onSignOutClick: () -> Unit
) {
    if (isExitAccDialog.value) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "No wifi",
                        tint = colorScheme.onPrimary,
                        modifier = Modifier.size(70.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(id = R.string.sign_out),
                        color = colorScheme.onPrimary,
                        style = typography.labelLarge,
                    )
                    Text(
                        text = stringResource(id = R.string.confirm_log_out),
                        color = colorScheme.onPrimary,
                        style = typography.labelMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        SecondaryButton(
                            shape = RoundedCornerShape(16.dp),
                            isSecondary = false,
                            modifier = Modifier
                                .height(65.dp),
                            label = R.string.cancel,
                            onClick = {
                                isExitAccDialog.value = false
                            }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        SecondaryButton(
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .height(65.dp),
                            label = R.string.sign_out,
                            onClick = {
                                isExitAccDialog.value = false
                                onSignOutClick()
                            }
                        )
                    }
                }
            }
        }
    }
}