package com.cook_and_share.presentation.ui.components

import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomTitle(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        modifier = modifier,
        color = colorScheme.onPrimary,
        style = typography.titleMedium
    )
}

@Composable
fun CustomLabel(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        modifier = modifier,
        color = colorScheme.onPrimary,
        style = typography.titleSmall
    )
}

@Composable
fun CustomSmallLabel(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        modifier = modifier,
        color = colorScheme.tertiary,
        style = typography.bodyLarge
    )
}