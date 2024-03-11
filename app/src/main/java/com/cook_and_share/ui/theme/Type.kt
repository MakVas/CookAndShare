package com.cook_and_share.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cook_and_share.R

val rubikFontFamily = FontFamily(
        Font(R.font.rubik_black, FontWeight(900), FontStyle.Normal),
        Font(R.font.rubik_blackitalic, FontWeight(900), FontStyle.Italic),
        Font(R.font.rubik_bold, FontWeight(700), FontStyle.Normal),
        Font(R.font.rubik_bolditalic, FontWeight(700), FontStyle.Italic),
        Font(R.font.rubik_extrabold, FontWeight(800), FontStyle.Normal),
        Font(R.font.rubik_extrabolditalic, FontWeight(800), FontStyle.Italic),
        Font(R.font.rubik_italic, FontWeight(400), FontStyle.Italic),
        Font(R.font.rubik_light, FontWeight(300), FontStyle.Normal),
        Font(R.font.rubik_lightitalic, FontWeight(300), FontStyle.Italic),
        Font(R.font.rubik_medium, FontWeight(500), FontStyle.Normal),
        Font(R.font.rubik_mediumitalic, FontWeight(500), FontStyle.Italic),
        Font(R.font.rubik_regular, FontWeight(400), FontStyle.Normal),
        Font(R.font.rubik_semibold, FontWeight(600), FontStyle.Normal),
        Font(R.font.rubik_semibolditalic, FontWeight(600), FontStyle.Italic),
)
// Set of Material typography styles to start with
val Typography = Typography(
        titleLarge = TextStyle(
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight(700),
                fontSize = 22.sp,
                lineHeight = 28.sp,
        ),
        titleMedium = TextStyle(
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight(700),
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
        ),
        titleSmall = TextStyle(
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight(600),
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
        ),
        headlineSmall = TextStyle(
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight(400),
                fontSize = 24.sp,
                lineHeight = 32.sp
        ),
        bodyLarge = TextStyle(
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight(400),
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
        ),
        bodyMedium = TextStyle(
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight(400),
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
        ),
        bodySmall = TextStyle(
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight(400),
                fontStyle = FontStyle.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
        ),
        labelMedium = TextStyle(
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight(500),
                fontStyle = FontStyle.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
        ),
        labelLarge = TextStyle(
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight(700),
                fontStyle = FontStyle.Normal,
                fontSize = 20.sp,
        ),
        displayLarge = TextStyle(
                fontFamily = rubikFontFamily,
                fontWeight = FontWeight(700),
                fontStyle = FontStyle.Normal,
                fontSize = 45.sp,
        ),
)