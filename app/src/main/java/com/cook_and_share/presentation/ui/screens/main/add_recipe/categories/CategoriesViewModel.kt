package com.cook_and_share.presentation.ui.screens.main.add_recipe.categories

import androidx.compose.runtime.mutableStateOf
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    logRepository: LogRepository,
    private val storageRepository: StorageRepository,
) : CookAndShareViewModel(logRepository) {

    val searchQuery = mutableStateOf("")

    fun getSearchCategoriesResult(): Flow<List<String>> {
        var resultFlow: Flow<List<String>> = flowOf(emptyList())
        launchCatching {
            resultFlow = storageRepository.searchCategories(
                query = searchQuery.value
            )
        }
        return resultFlow
    }
}