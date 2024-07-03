package com.cook_and_share.presentation.ui.screens.main.add_recipe.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cook_and_share.R
import com.cook_and_share.presentation.ui.components.CategoryItem
import com.cook_and_share.presentation.ui.components.CustomTextField
import com.cook_and_share.presentation.ui.components.CustomTitle
import com.cook_and_share.presentation.ui.components.TopAppBarBackIcon
import com.cook_and_share.presentation.ui.components.TopBar
import com.cook_and_share.presentation.ui.screens.main.add_recipe.AddRecipeViewModel
import com.cook_and_share.presentation.util.Constants.COLLECTION_NAME_CATEGORIES

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    popUp: () -> Unit,
    viewModel: AddRecipeViewModel
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val categories by viewModel.getSearchResult(COLLECTION_NAME_CATEGORIES).collectAsState(emptyList())

    CategoriesScreenContent(
        selectedCategories = viewModel.selectedCategories,
        categories = categories,
        popUp = popUp,
        scrollBehavior = scrollBehavior,
        onValueChange = viewModel.searchQuery,
        onCategoryClick = viewModel::onItemClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoriesScreenContent(
    selectedCategories: MutableState<List<String>>,
    onCategoryClick: (String, MutableState<List<String>>) -> Unit,
    categories: List<String>,
    popUp: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    onValueChange: MutableState<String>
) {
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                title = {
                    CustomTitle(text = stringResource(id = R.string.categories))
                },
                scrollBehavior = scrollBehavior,
                iconButton = {
                    TopAppBarBackIcon(popUp)
                }
            )
        }
    ) { values ->
        Box(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(colorScheme.background)
        ) {
            NestedScrolling(
                selectedCategories = selectedCategories,
                onCategoryClick = onCategoryClick,
                categories = categories,
                onValueChange = onValueChange
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    selectedCategories: MutableState<List<String>>,
    onCategoryClick: (String, MutableState<List<String>>) -> Unit,
    categories: List<String>,
    onValueChange: MutableState<String>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = 16.dp))

            CustomTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                icon = Icons.Default.Search,
                fieldLabel = stringResource(id = R.string.search),
                value = onValueChange.value,
                onValueChange = { onValueChange.value = it }
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))

        }
        item {
            Categories(
                selectedCategories = selectedCategories,
                onCategoryClick = onCategoryClick,
                categories = categories
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Categories(
    selectedCategories: MutableState<List<String>>,
    onCategoryClick: (String, MutableState<List<String>>) -> Unit,
    categories: List<String>
) {
    FlowRow(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 8.dp)
    ) {
        repeat(categories.size) {
            CategoryItem(
                modifier = Modifier
                    .padding(bottom = 8.dp, end = 8.dp),
                isClicked = selectedCategories.value.contains(categories[it]),
                category = categories[it],
                onClick = {
                    onCategoryClick(categories[it], selectedCategories)
                }
            )
        }
    }
}