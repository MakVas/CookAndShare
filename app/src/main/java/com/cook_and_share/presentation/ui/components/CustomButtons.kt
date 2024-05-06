package com.cook_and_share.presentation.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
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
            containerColor = colorScheme.secondary,
            contentColor = colorScheme.onSecondary
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
    shape: Shape = ButtonDefaults.elevatedShape,
    label: Int,
    onClick: () -> Unit,
    isSecondary: Boolean = true
) {
    val colors =
        if (isSecondary) {
            ButtonDefaults.elevatedButtonColors(
                containerColor = colorScheme.tertiary,
                contentColor = colorScheme.onTertiary
            )
        } else {
            ButtonDefaults.elevatedButtonColors(
                containerColor = colorScheme.secondary,
                contentColor = colorScheme.onSecondary
            )
        }
    ElevatedButton(
        onClick = onClick,
        shape = shape,
        colors = colors,
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 3.dp,
            pressedElevation = 0.dp
        ),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = label),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun TertiaryButton(
    modifier: Modifier,
    label: Int,
    icon: ImageVector?,
    isArrow: Boolean = false
) {
    Box(
        modifier = modifier
    ) {
        if (icon != null) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                imageVector = icon,
                contentDescription = "icon"
            )
        }
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
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp))
            .background(colorScheme.secondary)
    ) {
        Icon(
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterStart),
            imageVector = icon,
            contentDescription = "icon"
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 52.dp),
            text = stringResource(id = label),
            style = MaterialTheme.typography.bodyLarge
        )
        Switch(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 12.dp),
            colors = SwitchDefaults.colors(
                checkedBorderColor = colorScheme.tertiary,
                checkedTrackColor = colorScheme.secondary,
                checkedThumbColor = colorScheme.tertiary,
                uncheckedThumbColor = colorScheme.tertiary,
                uncheckedTrackColor = colorScheme.secondary,
                uncheckedBorderColor = colorScheme.tertiary,
            ),
            checked = isActive,
            onCheckedChange = { onSwitchToggle() }
        )
    }
}