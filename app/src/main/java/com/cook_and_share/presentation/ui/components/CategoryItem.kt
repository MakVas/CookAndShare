package com.cook_and_share.presentation.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryItem(
    isClicked: Boolean = false,
    category: String,
    backgroundColor: Color = if (isClicked) colorScheme.tertiary else colorScheme.secondary,
    textColor: Color = if (isClicked) colorScheme.secondary else colorScheme.onSecondary,
    onClick: () -> Unit
) {
    val scale = remember { Animatable(initialValue = 1f) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(key1 = isClicked) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(durationMillis = 50)
        )
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    Box(
        Modifier
            .padding(bottom = 8.dp, end = 8.dp)
            .scale(scale.value)
            .border(1.dp, colorScheme.onSecondary, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(8.dp),
            color = textColor
        )
    }
}