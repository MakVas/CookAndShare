package com.cook_and_share.presentation.ui.screens.main.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.presentation.ui.components.RecipeBottomSheet
import com.cook_and_share.presentation.ui.components.SearchItem
import com.cook_and_share.presentation.ui.components.SearchTopBar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    isTranslation: Boolean,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val searchProfileResults by viewModel.getSearchProfileResult().collectAsState(emptyList())
    val searchRecipeResults by viewModel.getSearchRecipeResult().collectAsState(emptyList())

    val tabIndex = remember { mutableIntStateOf(0) }
    val tabs = listOf(stringResource(id = R.string.recipes), stringResource(id = R.string.people))

    val sheetState = rememberModalBottomSheetState()
    val isSheetExpanded = rememberSaveable { mutableStateOf(false) }

    val recipe = remember { mutableStateOf(Recipe()) }

    val localLanguage = Locale.getDefault().language

    RecipeBottomSheet(
        isTranslation = isTranslation,
        localLanguage = localLanguage,
        identifyLanguage = viewModel::identifyLanguage,
        translateText = viewModel::translateText,
        recipe = recipe.value,
        sheetState = sheetState,
        isSheetExpanded = isSheetExpanded
    )
    SearchScreenContent(
        searchProfileResults = searchProfileResults,
        searchRecipeResults = searchRecipeResults,
        searchQuery = viewModel.searchQuery,
        tabIndex = tabIndex,
        tabs = tabs,
        recipe = recipe,
        isSheetExpanded = isSheetExpanded
    )
}

@Composable
private fun SearchScreenContent(
    searchProfileResults: List<Profile>,
    searchRecipeResults: List<Recipe>,
    searchQuery: MutableState<String>,
    tabIndex: MutableState<Int>,
    tabs: List<String>,
    recipe: MutableState<Recipe>,
    isSheetExpanded: MutableState<Boolean>
) {
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
            NestedScrolling(
                searchProfileResults = searchProfileResults,
                searchRecipeResults = searchRecipeResults,
                recipe = recipe,
                isSheetExpanded = isSheetExpanded,
                tabIndex = tabIndex
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    searchProfileResults: List<Profile>,
    searchRecipeResults: List<Recipe>,
    recipe: MutableState<Recipe>,
    isSheetExpanded: MutableState<Boolean>,
    tabIndex: MutableState<Int>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        when (tabIndex.value) {
            0 -> recipeSearch(
                recipe = recipe,
                searchRecipeResults = searchRecipeResults,
                isSheetExpanded = isSheetExpanded,
                modifier = Modifier.padding(horizontal = 16.dp))
            1 -> peopleSearch(
                searchProfileResults = searchProfileResults,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

private fun LazyListScope.recipeSearch(
    recipe: MutableState<Recipe>,
    searchRecipeResults: List<Recipe>,
    isSheetExpanded: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    items(searchRecipeResults) {recipeItem ->
        SearchItem(
            onClick = {
                recipe.value = recipeItem
                isSheetExpanded.value = true
            },
            defaultImage = R.drawable.no_image,
            image = recipeItem.imageUrl,
            title = recipeItem.title,
            text = recipeItem.author,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

private fun LazyListScope.peopleSearch(
    searchProfileResults: List<Profile>,
    modifier: Modifier = Modifier
) {

    items(searchProfileResults){ profile ->
        SearchItem(
            onClick = {
            },
            defaultImage = R.drawable.profile_default,
            image = profile.profileImage,
            title = profile.username,
            text = profile.email,
            modifier = modifier,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}