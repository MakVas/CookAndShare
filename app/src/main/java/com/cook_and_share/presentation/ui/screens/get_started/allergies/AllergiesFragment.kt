package com.cook_and_share.presentation.ui.screens.get_started.allergies

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
import androidx.compose.material3.MaterialTheme.colorScheme
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
fun AllergiesFragment(
    navigate: (String) -> Unit,
    viewModel: GetStartedViewModel
) {
    val allergiesList = remember {
        mutableStateOf(
            listOf(
                R.string.gluten,
                R.string.dairy,
                R.string.shellfish,
                R.string.peanut,
                R.string.treenut,
                R.string.fish,
                R.string.soy,
                R.string.egg,
                R.string.lactose,
            )
        )
    }
    val selectedAllergies = remember {
        mutableStateOf(emptyList<Int>())
    }

    AllergiesFragmentContent(
        onContinueClick = { viewModel.onAllergiesContinueClick(navigate) },
        selectedAllergies = selectedAllergies,
        onAllergyClick = viewModel::onItemClick,
        allergiesList = allergiesList.value
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun AllergiesFragmentContent(
    onContinueClick: () -> Unit,
    selectedAllergies: MutableState<List<Int>>,
    onAllergyClick: (Int, MutableState<List<Int>>) -> Unit,
    allergiesList: List<Int>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {
        Text(
            text = stringResource(id = R.string.select_allergies),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            textAlign = TextAlign.Center,
        )

        Allergies(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 64.dp),
            selectedAllergies = selectedAllergies,
            onAllergyClick = onAllergyClick,
            allergiesList = allergiesList
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
private fun Allergies(
    modifier: Modifier = Modifier,
    selectedAllergies: MutableState<List<Int>>,
    onAllergyClick: (Int, MutableState<List<Int>>) -> Unit,
    allergiesList: List<Int>
) {
    FlowRow(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        repeat(allergiesList.size) {
            CategoryItem(
                modifier = Modifier
                    .padding(bottom = 8.dp, end = 8.dp),
                isClicked = selectedAllergies.value.contains(allergiesList[it]),
                category = stringResource(id = allergiesList[it]),
                onClick = {
                    onAllergyClick(allergiesList[it], selectedAllergies)
                }
            )
        }
    }
}