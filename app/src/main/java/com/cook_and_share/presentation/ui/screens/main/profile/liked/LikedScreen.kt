package com.cook_and_share.presentation.ui.screens.main.profile.liked

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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.presentation.ui.components.RecipeBottomSheet
import com.cook_and_share.presentation.ui.components.RecipeItem
import com.cook_and_share.presentation.ui.components.TopAppBarBackIcon
import com.cook_and_share.presentation.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikedScreen(
    popUp: () -> Unit,
    viewModel: LikedScreenViewModel = hiltViewModel()
) {
    val likedRecipes = viewModel.likedRecipes.collectAsState(emptyList())

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val sheetState = rememberModalBottomSheetState()
    val isSheetExpanded = rememberSaveable { mutableStateOf(false) }

    RecipeBottomSheet(
        sheetState = sheetState,
        isSheetExpanded = isSheetExpanded
    )

    LikedScreenContent(
        isRecipeLiked = viewModel::isRecipeLiked,
        popUp = popUp,
        scrollBehavior = scrollBehavior,
        recipes = likedRecipes.value,
        onRecipeLikeClick = viewModel::onRecipeLikeClick,
        isSheetExpanded = isSheetExpanded
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LikedScreenContent(
    isRecipeLiked: (Recipe) -> Boolean,
    isSheetExpanded: MutableState<Boolean>,
    onRecipeLikeClick: (Recipe) -> Unit,
    popUp: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    recipes: List<Recipe>
) {
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                text = R.string.liked,
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
                isRecipeLiked = isRecipeLiked,
                recipes = recipes,
                isSheetExpanded = isSheetExpanded,
                onRecipeLikeClick = onRecipeLikeClick
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    isRecipeLiked: (Recipe) -> Boolean,
    recipes: List<Recipe>,
    isSheetExpanded: MutableState<Boolean>,
    onRecipeLikeClick: (Recipe) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = 16.dp))
        }
        subColumn(
            isRecipeLiked = isRecipeLiked,
            onRecipeLikeClick = onRecipeLikeClick,
            recipes = recipes,
            isSheetExpanded = isSheetExpanded
        )
    }
}

private fun LazyListScope.subColumn(
    isRecipeLiked: (Recipe) -> Boolean,
    onRecipeLikeClick: (Recipe) -> Unit,
    recipes: List<Recipe>,
    isSheetExpanded: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    items(recipes, key = { it.id }) { recipeItem ->
        val isLiked = isRecipeLiked(recipeItem)
        RecipeItem(
            isLiked = isLiked,
            onRecipeLikeClick = onRecipeLikeClick,
            recipe = recipeItem,
            onClick = {
                isSheetExpanded.value = true
            },
            modifier = modifier,
            isPreview = false
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}