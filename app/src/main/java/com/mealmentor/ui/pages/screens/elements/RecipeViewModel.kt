package com.mealmentor.ui.pages.screens.elements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mealmentor.logic.database.data.model.RecipeModel
import com.mealmentor.logic.database.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    val repository: RecipeRepository
): ViewModel(){

    private val _recipes = MutableLiveData<List<RecipeModel>>()
    val recipe: LiveData<List<RecipeModel>>
        get() = _recipes
    fun getRecipes(){
         _recipes.value = repository.getRecipes()
    }
}