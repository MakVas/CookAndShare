package com.cook_and_share.presentation.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun LikeButton(
    modifier: Modifier = Modifier,
    isLiked: Boolean,
    onRecipeLikeClick: () -> Unit,
    likes: String,
    imageVector: ImageVector =
        if (isLiked) Icons.Default.Favorite
        else Icons.Default.FavoriteBorder,
    color: Color =
        if (isLiked) MaterialTheme.colorScheme.surfaceTint
        else MaterialTheme.colorScheme.onPrimary
) {
    val scale = remember { Animatable(initialValue = 1f) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(key1 = isLiked) {
        scale.animateTo(
            targetValue = 0.5f,
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

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Box(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onRecipeLikeClick()
                },
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .scale(scale.value),
                imageVector = imageVector,
                tint = color,
                contentDescription = "Like Btn"
            )
        }
        Text(
            text = likes,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}