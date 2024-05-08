package com.cook_and_share.presentation.ui.screens.main.add_recipe

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.cook_and_share.R
import com.cook_and_share.domain.model.Ingredient
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.presentation.ui.components.snackbar.SnackbarManager
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Constants.RECIPE_ID
import com.cook_and_share.presentation.util.Main
import com.cook_and_share.presentation.util.idFromParameter
import com.cook_and_share.presentation.util.isValidRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    logRepository: LogRepository,
    private val authRepository: AuthRepository,
    private val storageRepository: StorageRepository,
) : CookAndShareViewModel(logRepository) {

    val profile = mutableStateOf(Profile())
    val recipe = mutableStateOf(Recipe())
    val recipeImage = mutableStateOf<Uri?>(null)
    val searchQuery = mutableStateOf("")
    val selectedCategories: MutableState<List<String>> = mutableStateOf(emptyList())
    val selectedIngredients: MutableState<List<String>> = mutableStateOf(emptyList())
    val selectedIngredientItems: MutableState<List<Ingredient>> = mutableStateOf(emptyList())
    fun getSearchResult(fieldName: String): Flow<List<String>> {
        var resultFlow: Flow<List<String>> = flowOf(emptyList())
        launchCatching {
            resultFlow = storageRepository.searchCategoriesOrIngredients(
                query = searchQuery.value,
                fieldName = fieldName
            )
        }
        return resultFlow
    }

    fun onItemClick(category: String, selectedCategories: MutableState<List<String>>) {
        if (selectedCategories.value.contains(category)) {
            selectedCategories.value -= category
        } else {
            selectedCategories.value += category
        }
    }

    fun onTitleChange(newValue: String) {
        recipe.value = recipe.value.copy(title = newValue)
    }

    fun onRecipeChange(newValue: String) {
        recipe.value = recipe.value.copy(recipe = newValue)
    }

    fun onPublishClick(popUpScreen: () -> Unit, selectedCategories: MutableState<List<String>>) {
        if (!recipe.value.title.isValidRecipe()){
            SnackbarManager.showMessage(R.string.title_error)
            return
        }

        if (!recipe.value.recipe.isValidRecipe()){
            SnackbarManager.showMessage(R.string.recipe_error)
            return
        }

        if (recipeImage.value == null){
            SnackbarManager.showMessage(R.string.image_error)
            return
        }

        if (selectedCategories.value.isEmpty()){
            SnackbarManager.showMessage(R.string.category_error)
            return
        }

        var recipeID: String
        launchCatching {
            recipe.value = recipe.value.copy(
                author = profile.value.username,
                tags = selectedCategories.value,
                ingredients = selectedIngredientItems.value
            )
            val editedTask = recipe.value
            recipeID = storageRepository.save(editedTask)
            storageRepository.update(
                editedTask.copy(
                    imageUrl = storageRepository.uploadRecipeImage(recipeID, recipeImage.value),
                    id = recipeID,
                    userID = authRepository.currentUserId
                )
            )
            authRepository.updateProfile(profile.value.copy(myRecipes = profile.value.myRecipes + recipeID))
            popUpScreen()
        }
    }

    fun onIngredientsClick(navigate: (String) -> Unit) {
        navigate(Main.IngredientsScreen.route)
    }

    fun onCategoryScreenClick(navigate: (String) -> Unit) {
        navigate(Main.CategoriesScreen.route)
    }

    fun getRecipeImage(uri: Uri?) {
        recipeImage.value = uri
    }


    init {
        val recipeId = savedStateHandle.get<String>(RECIPE_ID)
        if (recipeId != null) {
            launchCatching {
                recipe.value = storageRepository.getRecipe(recipeId.idFromParameter()) ?: Recipe()
            }
        }
    }

    init {
        launchCatching {
            profile.value = authRepository.getProfile(authRepository.currentUserId) ?: Profile()
        }
    }
}