package com.cook_and_share.presentation.ui.screens.main.add_recipe.add_recipe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Constants.RECIPE_ID
import com.cook_and_share.presentation.util.Main
import com.cook_and_share.presentation.util.idFromParameter
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun onTitleChange(newValue: String) {
        recipe.value = recipe.value.copy(title = newValue)
    }

    fun onUrlChange(newValue: String) {
        recipe.value = recipe.value.copy(imageUrl = newValue)
    }

    fun onTagsChange(newValue: List<String>) {
        recipe.value = recipe.value.copy(tags = newValue)
    }

    fun onIngredientsChange(newValue: List<Map<String, Int>>) {
        recipe.value = recipe.value.copy(ingredients = newValue)
    }

    fun onRecipeChange(newValue: String) {
        recipe.value = recipe.value.copy(recipe = newValue)
    }
    fun onPublishClick(popUpScreen: () -> Unit) {
        launchCatching {
            recipe.value = recipe.value.copy(author = profile.value.username)
            val editedTask = recipe.value
            storageRepository.save(editedTask)
            popUpScreen()
        }
    }
    fun onIngredientsClick(navigate: (String) -> Unit) {
        navigate(Main.IngredientsScreen.route)
    }

    fun onCategoryClick(navigate: (String) -> Unit) {
        navigate(Main.CategoriesScreen.route)
    }

    fun onRecipeClick(navigate: (String) -> Unit) {
       //TODO: navigate(Screens.RecipeScreen.route)
    }
}