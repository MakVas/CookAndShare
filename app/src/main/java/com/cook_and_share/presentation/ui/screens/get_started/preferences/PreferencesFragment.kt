package com.cook_and_share.presentation.ui.screens.get_started.preferences

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.CategoryItem
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.screens.get_started.GetStartedViewModel

@Composable
fun PreferencesFragment(
    navigate: (String) -> Unit,
    viewModel: GetStartedViewModel
) {

    val preferencesList = remember {
        mutableStateOf(
            listOf(
                R.string.gluten_free,
                R.string.vegetarian,
                R.string.vegan,
                R.string.diet,
            )
        )
    }
    val selectedPreferences = remember {
        mutableStateOf(emptyList<Int>())
    }

    PreferencesFragmentContent(
        onContinueClick = { viewModel.onPreferencesContinueClick(navigate) },
        selectedPreferences = selectedPreferences,
        onPreferenceClick = viewModel::onItemClick,
        preferencesList = preferencesList.value
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun PreferencesFragmentContent(
    onContinueClick: () -> Unit,
    selectedPreferences: MutableState<List<Int>>,
    onPreferenceClick: (Int, MutableState<List<Int>>) -> Unit,
    preferencesList: List<Int>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = stringResource(id = R.string.select_preferences),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            textAlign = TextAlign.Center,
        )

        Preferences(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 48.dp, horizontal = 16.dp),
            selectedPreferences = selectedPreferences,
            onPreferenceClick = onPreferenceClick,
            preferencesList = preferencesList
        )

        SecondaryButton(
            modifier = Modifier
                .padding(horizontal = 70.dp, vertical = 16.dp)
                .height(65.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            label = R.string.continue_text,
            onClick = onContinueClick
        )
    }
}

@Composable
private fun Preferences(
    modifier: Modifier = Modifier,
    selectedPreferences: MutableState<List<Int>>,
    onPreferenceClick: (Int, MutableState<List<Int>>) -> Unit,
    preferencesList: List<Int>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(preferencesList, key = { it }) { preferencesItem ->
            Spacer(modifier = Modifier.height(16.dp))

            CategoryItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp),
                isClicked = selectedPreferences.value.contains(preferencesItem),
                category = stringResource(id = preferencesItem),
                onClick = {
                    onPreferenceClick(preferencesItem, selectedPreferences)
                }
            )
        }
    }
}