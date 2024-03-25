package com.cook_and_share.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cook_and_share.R
import com.cook_and_share.core.presentation.ui.components.SearchItem
import com.cook_and_share.core.presentation.ui.components.SearchTopBar

@Composable
fun SearchScreen(
    isSheetExpanded: MutableState<Boolean>,
) {
    val searchQuery = remember { mutableStateOf("") }
    val tabIndex = remember { mutableIntStateOf(0) }
    val tabs = listOf(stringResource(id = R.string.recipes), stringResource(id = R.string.people))

    Scaffold(
        topBar = {
            SearchTopBar(
                text = R.string.search,
                searchQuery = searchQuery,
                tabIndex = tabIndex,
                tabs = tabs
            )
        }
    ) { values ->
        Box(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            NestedScrolling(isSheetExpanded, tabIndex)
        }
    }
}

@Composable
private fun NestedScrolling(isSheetExpanded: MutableState<Boolean>, tabIndex: MutableState<Int>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        when (tabIndex.value) {
            0 -> recipeSearch(isSheetExpanded, Modifier.padding(horizontal = 16.dp))
            1 -> peopleSearch(Modifier.padding(horizontal = 16.dp))
        }
    }
}

private fun LazyListScope.recipeSearch(
    isSheetExpanded: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    items(30) {
        SearchItem(
            onClick = {
                isSheetExpanded.value = true
            },
            image = if (it % 2 == 0) R.drawable.image1 else R.drawable.image2,
            title = "Recipe $it",
            text = "username",
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

private fun LazyListScope.peopleSearch(modifier: Modifier = Modifier) {
    items(30) {
        SearchItem(
            onClick = {
            },
            image = R.drawable.profile_default,
            title = "User $it",
            text = "Name Surname",
            modifier = modifier,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}