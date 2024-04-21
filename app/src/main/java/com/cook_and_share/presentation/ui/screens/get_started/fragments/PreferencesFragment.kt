package com.cook_and_share.presentation.ui.screens.get_started.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.SecondaryButton

@Composable
fun PreferencesFragment() {

    PreferencesFragmentContent()
}

@Composable
private fun PreferencesFragmentContent() {
    Column (modifier = Modifier.fillMaxSize()){
        SecondaryButton(
            modifier = Modifier
                .padding(horizontal = 80.dp)
                .height(65.dp)
                .fillMaxWidth(),
            label = R.string.continue_text,
            onClick = {}
        )
    }
}