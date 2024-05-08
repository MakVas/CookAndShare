package com.cook_and_share.presentation.ui.screens.get_started.dislikes

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun DislikesFragment(
    navigate: (String) -> Unit,
    viewModel: GetStartedViewModel
) {

    val dislikesList = remember {
        mutableStateOf(
            listOf(
                R.string.olives,
                R.string.pineapple,
                R.string.onion,
                R.string.mustard,
                R.string.pepper,
                R.string.mushrooms,
                R.string.fish,
                R.string.mayonnaise,
                R.string.broccoli,
                R.string.garlic
            )
        )
    }
    val selectedPreferences = remember {
        mutableStateOf(emptyList<Int>())
    }

    DislikesFragmentContent(
        onContinueClick = { viewModel.onDislikesContinueClick(navigate) },
        selectedDislikes = selectedPreferences,
        onDislikeClick = viewModel::onItemClick,
        dislikesList = dislikesList.value
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun DislikesFragmentContent(
    onContinueClick: () -> Unit,
    selectedDislikes: MutableState<List<Int>>,
    onDislikeClick: (Int, MutableState<List<Int>>) -> Unit,
    dislikesList: List<Int>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = stringResource(id = R.string.select_dislikes),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            textAlign = TextAlign.Start,
        )

        Preferences(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 64.dp),
            selectedDislikes = selectedDislikes,
            onDislikesClick = onDislikeClick,
            dislikesList = dislikesList
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Preferences(
    modifier: Modifier = Modifier,
    selectedDislikes: MutableState<List<Int>>,
    onDislikesClick: (Int, MutableState<List<Int>>) -> Unit,
    dislikesList: List<Int>
) {
    FlowRow(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        repeat(dislikesList.size) {
            CategoryItem(
                modifier = Modifier
                    .padding(bottom = 8.dp, end = 8.dp),
                isClicked = selectedDislikes.value.contains(dislikesList[it]),
                category = stringResource(id = dislikesList[it]),
                onClick = {
                    onDislikesClick(dislikesList[it], selectedDislikes)
                }
            )
        }
    }
}