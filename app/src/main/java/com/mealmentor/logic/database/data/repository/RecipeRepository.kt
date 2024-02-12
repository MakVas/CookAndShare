package com.mealmentor.logic.database.data.repository

import com.mealmentor.logic.database.data.model.RecipeModel

interface RecipeRepository {
    fun getRecipes(): List<RecipeModel>
}