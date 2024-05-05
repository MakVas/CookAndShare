package com.cook_and_share.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    modifier: Modifier,
    label: Int,
    icon: ImageVector,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 1.dp,
            pressedElevation = 0.dp
        ),
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 12.dp),
    ) {
        TertiaryButton(
            modifier = Modifier.fillMaxSize(),
            label = label,
            icon = icon,
            isArrow = true
        )
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    label: Int,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        shape = ButtonDefaults.elevatedShape,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 3.dp,
            pressedElevation = 0.dp
        ),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = label),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun TertiaryButton(
    modifier: Modifier,
    label: Int,
    icon: ImageVector,
    isArrow: Boolean = false
) {
    Box(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterStart),
            imageVector = icon,
            contentDescription = "icon"
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 40.dp),
            text = stringResource(id = label),
            style = MaterialTheme.typography.bodyLarge
        )
        if (isArrow) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "arrow"
            )
        }
    }
}

@Composable
fun SwitcherButton(
    modifier: Modifier,
    label: Int,
    icon: ImageVector,
    isActive: Boolean,
    onSwitchToggle: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterStart),
            imageVector = icon,
            contentDescription = "icon"
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 40.dp),
            text = stringResource(id = label),
            style = MaterialTheme.typography.bodyLarge
        )
        Switch(
            modifier = Modifier.align(Alignment.CenterEnd),
            colors = SwitchDefaults.colors(
                checkedBorderColor = MaterialTheme.colorScheme.tertiary,
                checkedTrackColor = MaterialTheme.colorScheme.secondary,
                checkedThumbColor = MaterialTheme.colorScheme.tertiary,
                uncheckedThumbColor = MaterialTheme.colorScheme.tertiary,
                uncheckedTrackColor = MaterialTheme.colorScheme.secondary,
                uncheckedBorderColor = MaterialTheme.colorScheme.tertiary,
            ),
            checked = isActive,
            onCheckedChange = { onSwitchToggle() }
        )
    }
}