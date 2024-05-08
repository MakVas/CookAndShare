package com.cook_and_share.presentation.ui.screens.get_started

import androidx.compose.runtime.MutableState
import com.cook_and_share.domain.repository.LogRepository
import com.cook_and_share.presentation.ui.screens.CookAndShareViewModel
import com.cook_and_share.presentation.util.GetStarted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetStartedViewModel @Inject constructor(
    logRepository: LogRepository
) : CookAndShareViewModel(logRepository) {

    fun onAllergiesContinueClick(navigate: (String) -> Unit) {
        navigate(GetStarted.Dislikes.route)
    }

    fun onDislikesContinueClick(navigate: (String) -> Unit) {
        navigate(GetStarted.SignUpScreen.route)
    }

    fun onPreferencesContinueClick(navigate: (String) -> Unit) {
        navigate(GetStarted.Allergies.route)
    }

    fun onItemClick(item: Int, selectedItems: MutableState<List<Int>>) {
        if (selectedItems.value.contains(item)) {
            selectedItems.value -= item
        } else {
            selectedItems.value += item
        }
    }
}