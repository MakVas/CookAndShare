package com.cook_and_share.presentation.ui.screens.get_started.allergies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.screens.get_started.GetStartedViewModel

@Composable
fun AllergiesFragment(
    navigate: (String) -> Unit,
    viewModel: GetStartedViewModel
) {

    AllergiesFragmentContent(
        onContinueClick = { viewModel.onAllergiesContinueClick(navigate) }
    )
}

@Composable
private fun AllergiesFragmentContent(
    onContinueClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Allergies")

        SecondaryButton(
            modifier = Modifier
                .padding(horizontal = 70.dp, vertical = 16.dp)
                .height(65.dp)
                .fillMaxWidth(),
            label = R.string.continue_text,
            onClick = onContinueClick
        )
    }
}