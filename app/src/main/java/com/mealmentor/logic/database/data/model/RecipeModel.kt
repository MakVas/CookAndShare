package com.mealmentor.logic.database.data.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class RecipeModel(
    val id: String,
    val name: String,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val imageUrl: String,
    @ServerTimestamp
    val date: Date
)
