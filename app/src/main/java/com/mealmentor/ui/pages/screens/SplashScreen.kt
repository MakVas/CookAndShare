package com.mealmentor.ui.pages.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import com.mealmentor.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    function: () -> Unit
) {
    val alpha = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(1500)
        )
        function()
        delay(2000)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.alpha(alpha.value),
            tint = MaterialTheme.colorScheme.onPrimary,
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Meal Mentor Logo"
        )
    }
}