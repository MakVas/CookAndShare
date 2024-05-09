package com.cook_and_share.presentation.ui.screens.main.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.domain.repository.TranslateRepository
import com.cook_and_share.domain.use_cases.LikeRecipeUseCase
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Constants.RECIPE_NAME_FIELD
import com.cook_and_share.presentation.util.Constants.USERNAME_FIELD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val storageRepository: StorageRepository,
    private val translateRepository: TranslateRepository,
    private val likeRecipeUseCase: LikeRecipeUseCase,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {

    val searchQuery = mutableStateOf("")
    val searchCategoryQuery = mutableStateOf("")
    val searchIngredientQuery = mutableStateOf("")

    val selectedCategories: MutableState<List<String>> = mutableStateOf(emptyList())
    val selectedIngredients: MutableState<List<String>> = mutableStateOf(emptyList())
    fun getSearchResult(query: String, fieldName: String): Flow<List<String>> {
        var resultFlow: Flow<List<String>> = flowOf(emptyList())
        launchCatching {
            resultFlow = storageRepository.searchCategoriesOrIngredients(
                query = query,
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
    fun getSearchProfileResult(): Flow<List<Profile>> {
        var resultFlow: Flow<List<Profile>> = flowOf(emptyList())
        launchCatching {
            resultFlow = storageRepository.searchProfiles(
                query = searchQuery.value,
                fieldName = USERNAME_FIELD
            )
        }
        return resultFlow
    }

    fun identifyLanguage(text: String): String {
        val second = mutableStateOf("")
        launchCatching {
            second.value = translateRepository.detectLanguage(text)
        }
        return second.value
    }

    fun translateText(
        text: String,
        sourceLanguage: String,
        targetLanguage: String,
        callback: (String) -> Unit
    ) {
        launchCatching {
            translateRepository.translateText(
                text,
                sourceLanguage,
                targetLanguage
            ) { translatedText ->
                callback(translatedText)
            }
        }
    }

    fun getSearchRecipeResult(categories: List<String>, ingredients: List<String>): Flow<List<Recipe>> {
        var resultFlow: Flow<List<Recipe>> = flowOf(emptyList())
        launchCatching {
            resultFlow = storageRepository.searchRecipes(
                query = searchQuery.value,
                fieldName = RECIPE_NAME_FIELD
            ).map { recipes ->
                recipes.filter { recipe ->
                    recipe.tags.any { category ->
                        categories.contains(category)
                    } || recipe.ingredients.any { ingredient ->
                        ingredients.contains(ingredient.name)
                    }
                }
            }
        }
        return resultFlow
    }

    fun isRecipeLiked(recipe: Recipe): Boolean {
        return likeRecipeUseCase.isRecipeLiked(recipe)
    }

    fun onRecipeLikeClick(recipe: Recipe) {
        launchCatching {
            likeRecipeUseCase.onRecipeLikeClick(recipe)
        }
    }
}
