package com.mealmentor.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = CustomDark1,
    secondary = CustomDark2,
    secondaryContainer = CustomMiddle,
    tertiary = CustomDark3,
    onPrimary = CustomDark4,
    onSecondary = CustomDark4,
    onTertiary = CustomDark1,
    background = CustomDark1,
    inversePrimary = Color.White,
    surface = CustomDark1,
    onSurface = CustomDark2Trans
)

private val LightColorScheme = lightColorScheme(
    primary = CustomYellow,
    secondary = CustomLightYellow,
    secondaryContainer = CustomMiddleYellow,
    tertiary = CustomChocolate,
    onPrimary = CustomChocolate,
    onSecondary = CustomChocolate,
    onTertiary = CustomYellow,
    background = CustomYellow,
    inversePrimary = Color.Black,
    surface = CustomYellow,
    onSurface = CustomLightYellowTrans
    /* Other default colors to override
background = Color(0xFFFFFBFE),
surface = Color(0xFFFFFBFE),
onPrimary = Color.White,
onSecondary = Color.White,
onTertiary = Color.White,
onBackground = Color(0xFF1C1B1F),
onSurface = Color(0xFF1C1B1F),
*/
)

@Composable
fun MealMentorKotlinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when (darkTheme) {
        true -> DarkColorScheme
        false -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            window.navigationBarColor = colorScheme.secondary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}