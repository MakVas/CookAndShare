package com.mealmentor.presentation.main.screens.custom

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CustomPagerIndicator(
    currentIndex: Int,
    elementsNumber: Int,
    modifier: Modifier = Modifier
) {
    if (elementsNumber > 1)
        Box(
            modifier = modifier
                .fillMaxHeight(0.1f)
                .padding(10.dp)
                .border(1.dp, colorScheme.secondaryContainer, CircleShape)
        ) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
                modifier = Modifier
                    .clip(CircleShape)
                    .background(colorScheme.secondary),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(elementsNumber) { index ->
                    val bgColor by animateColorAsState(
                        if (currentIndex == index) colorScheme.onSecondary
                        else colorScheme.primary,
                        tween(100),
                        ""
                    )
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(bgColor)
                    )
                }
            }
        }
}
