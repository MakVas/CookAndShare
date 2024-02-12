package com.mealmentor.logic.database.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.mealmentor.logic.database.data.model.RecipeModel
import java.util.Date

class RecipeRepositoryImp(
    val database: FirebaseFirestore
): RecipeRepository {

    override fun getRecipes(): List<RecipeModel> {
        return arrayListOf(
            RecipeModel(
                "1",
                "Pasta",
                "Pasta with tomato sauce",
                arrayListOf("Pasta", "Tomato Sauce"),
                arrayListOf("Boil pasta", "Add tomato sauce"),
                "https://www.google.com",
                Date()
            ),
            RecipeModel(
                "2",
                "Pizza",
                "Pizza with pepperoni",
                arrayListOf("Dough", "Tomato Sauce", "Cheese", "Pepperoni"),
                arrayListOf("Roll dough", "Add tomato sauce", "Add cheese", "Add pepperoni"),
                "https://www.google.com",
                Date()
            ),
            RecipeModel(
                "3",
                "Salad",
                "Salad with lettuce and tomato",
                arrayListOf("Lettuce", "Tomato", "Vinaigrette"),
                arrayListOf("Chop lettuce", "Chop tomato", "Add vinaigrette"),
                "https://www.google.com",
                Date()
            ),
            RecipeModel(
                "4",
                "Burger",
                "Burger with cheese and bacon",
                arrayListOf("Bread", "Beef", "Cheese", "Bacon"),
                arrayListOf("Cook beef", "Add cheese", "Add bacon"),
                "https://www.google.com",
                Date()
            ),
            RecipeModel(
                "5",
                "Tacos",
                "Tacos with beef and guacamole",
                arrayListOf("Tortilla", "Beef", "Guacamole"),
                arrayListOf("Cook beef", "Add guacamole"),
                "https://www.google.com",
                Date()
            )
        )
    }
}