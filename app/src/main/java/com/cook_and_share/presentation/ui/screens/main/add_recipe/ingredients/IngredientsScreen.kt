package com.cook_and_share.presentation.ui.screens.main.add_recipe.ingredients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.cook_and_share.R
import com.cook_and_share.domain.model.Ingredient
import com.cook_and_share.presentation.ui.components.IngredientItem
import com.cook_and_share.presentation.ui.components.IngredientsBottomSheet
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.components.TopAppBarBackIcon
import com.cook_and_share.presentation.ui.components.TopBar
import com.cook_and_share.presentation.ui.screens.main.add_recipe.AddRecipeViewModel
import com.cook_and_share.presentation.util.Constants.COLLECTION_NAME_INGREDIENTS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientsScreen(
    popUp: () -> Unit,
    viewModel: AddRecipeViewModel
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val ingredients by viewModel.getSearchResult(COLLECTION_NAME_INGREDIENTS)
        .collectAsState(emptyList())

    val sheetState = rememberModalBottomSheetState()
    val isSheetExpanded = rememberSaveable { mutableStateOf(false) }

    val unitList = listOf("g", "kg", "ml", "l", "tbsp", "tsp", "cup", "glass", "units")

    IngredientsBottomSheet(
        onValueChange = viewModel.searchQuery,
        selectedIngredients = viewModel.selectedIngredients,
        ingredients = ingredients,
        sheetState = sheetState,
        isSheetExpanded = isSheetExpanded,
        onIngredientClick = viewModel::onItemClick
    )
    IngredientsScreenContent(
        unitList = unitList,
        isSheetExpanded = isSheetExpanded,
        popUp = popUp,
        scrollBehavior = scrollBehavior,
        selectedIngredients = viewModel.selectedIngredients,
        selectedIngredientItems = viewModel.selectedIngredientItems
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun IngredientsScreenContent(
    unitList: List<String>,
    selectedIngredients: MutableState<List<String>>,
    isSheetExpanded: MutableState<Boolean>,
    popUp: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    selectedIngredientItems: MutableState<List<Ingredient>>
) {
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                text = R.string.ingredients,
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
                .background(MaterialTheme.colorScheme.background)
        ) {
            NestedScrolling(
                unitList = unitList,
                selectedIngredients = selectedIngredients,
                isSheetExpanded = isSheetExpanded,
                selectedIngredientItems = selectedIngredientItems
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    unitList: List<String>,
    selectedIngredients: MutableState<List<String>>,
    isSheetExpanded: MutableState<Boolean>,
    selectedIngredientItems: MutableState<List<Ingredient>>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(selectedIngredients.value) {name ->
            Spacer(modifier = Modifier.height(16.dp))

            IngredientItem(
                modifier = Modifier,
                name = name,
                unitList = unitList,
                selectedIngredientItems = selectedIngredientItems,
                onButtonClick = {
                    selectedIngredients.value -= name
                },
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))

            SecondaryButton(
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth(),
                label = R.string.add_ingredient,
                onClick = {
                    isSheetExpanded.value = true
                }
            )
        }
    }
}