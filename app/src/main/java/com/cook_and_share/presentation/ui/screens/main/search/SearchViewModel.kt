package com.cook_and_share.presentation.ui.screens.main.search

import androidx.compose.runtime.mutableStateOf
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val storageRepository: StorageRepository,
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {

    val searchQuery = mutableStateOf("")
    fun getSearchProfileResult():Flow<List<Profile>> {
        var resultFlow: Flow<List<Profile>> = flowOf(emptyList())
        launchCatching {
            resultFlow = storageRepository.searchProfiles(
                query = searchQuery.value,
                fieldName = Constants.USERNAME_FIELD
            )
        }
        return resultFlow
    }
}
