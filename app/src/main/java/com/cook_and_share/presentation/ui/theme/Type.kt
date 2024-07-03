package com.cook_and_share.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cook_and_share.R

val montserratFontFamily = FontFamily(
    Font(R.font.montserrat_black, FontWeight(900), FontStyle.Normal),
    Font(R.font.montserrat_black_italic, FontWeight(900), FontStyle.Italic),
    Font(R.font.montserrat_bold, FontWeight(700), FontStyle.Normal),
    Font(R.font.montserrat_bold_italic, FontWeight(700), FontStyle.Italic),
    Font(R.font.montserrat_extra_bold, FontWeight(800), FontStyle.Normal),
    Font(R.font.montserrat_extra_bold_italic, FontWeight(800), FontStyle.Italic),
    Font(R.font.montserrat_extra_light, FontWeight(200), FontStyle.Normal),
    Font(R.font.montserrat_extra_light_italic, FontWeight(200), FontStyle.Italic),
    Font(R.font.montserrat_italic, FontWeight(400), FontStyle.Italic),
    Font(R.font.montserrat_light, FontWeight(300), FontStyle.Normal),
    Font(R.font.montserrat_light_italic, FontWeight(300), FontStyle.Italic),
    Font(R.font.montserrat_medium, FontWeight(500), FontStyle.Normal),
    Font(R.font.montserrat_medium_italic, FontWeight(500), FontStyle.Italic),
    Font(R.font.montserrat_regular, FontWeight(400), FontStyle.Normal),
    Font(R.font.montserrat_semi_bold, FontWeight(600), FontStyle.Normal),
    Font(R.font.montserrat_semi_bold_italic, FontWeight(600), FontStyle.Italic),
    Font(R.font.montserrat_thin, FontWeight(100), FontStyle.Normal),
    Font(R.font.montserrat_thin_italic, FontWeight(100), FontStyle.Italic),
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight(700),
        fontSize = 22.sp,
        lineHeight = 28.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight(700),
        fontStyle = FontStyle.Normal,
        fontSize = 18.sp
    ),
    titleSmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight(600),
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight(400),
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight(400),
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight(400),
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight(400),
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight(500),
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight(700),
        fontStyle = FontStyle.Normal,
        fontSize = 20.sp,
    ),
    displayLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight(700),
        fontStyle = FontStyle.Normal,
        fontSize = 45.sp,
    )
)