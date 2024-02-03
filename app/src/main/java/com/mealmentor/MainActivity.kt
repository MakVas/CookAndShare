package com.mealmentor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mealmentor.ui.screens.LoginPage
import com.mealmentor.ui.theme.MealMentorKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealMentorKotlinTheme {
                LoginPage()
            }
        }
    }
}