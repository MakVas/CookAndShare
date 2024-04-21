package com.cook_and_share.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(progress: Float){
    Box {
        HorizontalDivider(
            modifier = Modifier
                .padding(start = 0.dp, end = 48.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            thickness = 8.dp,
            color = colorScheme.secondary
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(start = 0.dp, end = 48.dp)
                .fillMaxWidth(progress/100f)
                .clip(RoundedCornerShape(8.dp)),
            thickness = 8.dp,
            color = colorScheme.onSecondary
        )
    }
}